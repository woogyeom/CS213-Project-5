package rucafe.ordermanager;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.Arrays;

public class SandwichFragment extends Fragment {

    private RadioGroup breadRadioGroup;
    private RadioGroup proteinRadioGroup;
    private RadioButton bagelRadioButton;
    private RadioButton wheatToastRadioButton;
    private RadioButton sourDoughRadioButton;
    private RadioButton beefRadioButton;
    private RadioButton fishRadioButton;
    private RadioButton chickenRadioButton;
    private CheckBox cheeseCheckBox;
    private CheckBox lettuceCheckBox;
    private CheckBox tomatoCheckBox;
    private CheckBox onionCheckBox;
    private TextView sandwichSubtotalTextView;
    private Button addToOrderButton;

    private final OrderList orderList = OrderList.getInstance();
    private Sandwich sandwich;

    public SandwichFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sandwich, container, false);

        // Initialize views
        breadRadioGroup = view.findViewById(R.id.breadRadioGroup);
        proteinRadioGroup = view.findViewById(R.id.proteinRadioGroup);
        bagelRadioButton = view.findViewById(R.id.bagel);
        wheatToastRadioButton = view.findViewById(R.id.wheatToast);
        sourDoughRadioButton = view.findViewById(R.id.sourDough);
        beefRadioButton = view.findViewById(R.id.beef);
        fishRadioButton = view.findViewById(R.id.fish);
        chickenRadioButton = view.findViewById(R.id.chicken);
        cheeseCheckBox = view.findViewById(R.id.cheese);
        lettuceCheckBox = view.findViewById(R.id.lettuce);
        tomatoCheckBox = view.findViewById(R.id.tomato);
        onionCheckBox = view.findViewById(R.id.onion);
        sandwichSubtotalTextView = view.findViewById(R.id.sandwich_subtotal);
        addToOrderButton = view.findViewById(R.id.sandwich_add_to_order);

        sandwich = new Sandwich("Bagel", SandwichProtein.BEEF, new boolean[]{false, false, false, false}, 1);
        updateSubtotal();

        breadRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String breadType = "Bagel";
                if (checkedId == R.id.bagel) {
                    breadType = "Bagel";
                } else if (checkedId == R.id.wheatToast) {
                    breadType = "Wheat Toast";
                } else if (checkedId == R.id.sourDough) {
                    breadType = "Sour Dough";
                }
                sandwich.setBread(breadType);
                updateSubtotal();
            }
        });

        proteinRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.beef) {
                    sandwich.setProtein(SandwichProtein.BEEF);
                } else if (checkedId == R.id.fish) {
                    sandwich.setProtein(SandwichProtein.FISH);
                } else if (checkedId == R.id.chicken) {
                    sandwich.setProtein(SandwichProtein.CHICKEN);
                }
                updateSubtotal();
            }
        });

        CompoundButton.OnCheckedChangeListener checkBoxListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int checkBoxId = buttonView.getId();
                if (checkBoxId == R.id.cheese) {
                    sandwich.setAdd_ons(0, isChecked);
                } else if (checkBoxId == R.id.lettuce) {
                    sandwich.setAdd_ons(1, isChecked);
                } else if (checkBoxId == R.id.tomato) {
                    sandwich.setAdd_ons(2, isChecked);
                } else if (checkBoxId == R.id.onion) {
                    sandwich.setAdd_ons(3, isChecked);
                }
                updateSubtotal();
            }
        };

        cheeseCheckBox.setOnCheckedChangeListener(checkBoxListener);
        lettuceCheckBox.setOnCheckedChangeListener(checkBoxListener);
        tomatoCheckBox.setOnCheckedChangeListener(checkBoxListener);
        onionCheckBox.setOnCheckedChangeListener(checkBoxListener);

        addToOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Do you want to add to the current order?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                addToOrder();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }
        });

        return view;
    }

    private void addToOrder() {
        Sandwich sandwich_to_add = new Sandwich(sandwich.getBread(), sandwich.getProtein(), Arrays.copyOf(sandwich.getAdd_ons(), sandwich.getAdd_ons().length), 1);
        Order curOrder = orderList.getCurOrder();
        if (curOrder.find(sandwich_to_add) == null) {
            curOrder.addItem(sandwich_to_add);
        } else {
            curOrder.find(sandwich_to_add).setQuantity(curOrder.find(sandwich_to_add).getQuantity() + sandwich_to_add.getQuantity());
        }
        Toast.makeText(getContext(), "Added to Order", Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("SetTextI18n")
    private void updateSubtotal() {
        double subTotal = sandwich.price();
        @SuppressLint("DefaultLocale") String formattedSubTotal = String.format("%.2f", subTotal);
        sandwichSubtotalTextView.setText("SubTotal: " + formattedSubTotal);
    }
}
