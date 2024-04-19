package rucafe.ordermanager;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CoffeeFragment extends Fragment {

    private CheckBox sweetCreamCheckBox;
    private CheckBox mochaCheckBox;
    private CheckBox frenchVanillaCheckBox;
    private CheckBox caramelCheckBox;
    private CheckBox irishCreamCheckBox;
    private Spinner coffeeSizeSpinner;
    private Spinner coffeeQuantitySpinner;
    private TextView coffeeSubtotalTextView;
    private Button addToOrderButton;

    private final OrderList orderList = OrderList.getInstance();
    private Coffee coffee;

    public CoffeeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_coffee, container, false);

        // Initialize views
        sweetCreamCheckBox = view.findViewById(R.id.sweetCream);
        mochaCheckBox = view.findViewById(R.id.mocha);
        frenchVanillaCheckBox = view.findViewById(R.id.frenchVanilla);
        caramelCheckBox = view.findViewById(R.id.caramel);
        irishCreamCheckBox = view.findViewById(R.id.irishCream);
        coffeeSizeSpinner = view.findViewById(R.id.coffeeSize);
        coffeeQuantitySpinner = view.findViewById(R.id.coffeeQuantity);
        coffeeSubtotalTextView = view.findViewById(R.id.coffeeSubtotal);
        addToOrderButton = view.findViewById(R.id.coffee_add_to_order);

        coffee = new Coffee(CoffeeSize.SHORT, new boolean[]{false, false, false, false, false}, 1);
        updateSubtotal();

        CompoundButton.OnCheckedChangeListener checkBoxListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int checkBoxId = buttonView.getId();
                if (checkBoxId == R.id.sweetCream) {
                    coffee.setAdd_in(0, isChecked);
                } else if (checkBoxId == R.id.frenchVanilla) {
                    coffee.setAdd_in(1, isChecked);
                } else if (checkBoxId == R.id.irishCream) {
                    coffee.setAdd_in(2, isChecked);
                } else if (checkBoxId == R.id.caramel) {
                    coffee.setAdd_in(3, isChecked);
                } else if (checkBoxId == R.id.mocha) {
                    coffee.setAdd_in(4, isChecked);
                }
                updateSubtotal();
            }
        };

        sweetCreamCheckBox.setOnCheckedChangeListener(checkBoxListener);
        mochaCheckBox.setOnCheckedChangeListener(checkBoxListener);
        frenchVanillaCheckBox.setOnCheckedChangeListener(checkBoxListener);
        caramelCheckBox.setOnCheckedChangeListener(checkBoxListener);
        irishCreamCheckBox.setOnCheckedChangeListener(checkBoxListener);

        List<String> sizeList = new ArrayList<>();
        for (CoffeeSize size : CoffeeSize.values()) {
            sizeList.add(size.name());
        }
        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, sizeList);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coffeeSizeSpinner.setAdapter(sizeAdapter);

        coffeeSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CoffeeSize selectedSize = CoffeeSize.values()[position];
                coffee.setCoffeeSize(selectedSize);
                updateSubtotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        List<Integer> quantityList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            quantityList.add(i);
        }
        ArrayAdapter<Integer> quantityAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, quantityList);
        quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coffeeQuantitySpinner.setAdapter(quantityAdapter);

        coffeeQuantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int selectedQuantity = quantityList.get(position);
                coffee.setQuantity(selectedQuantity);
                updateSubtotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

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

    public void reset() {
        sweetCreamCheckBox.setChecked(false);
        mochaCheckBox.setChecked(false);
        frenchVanillaCheckBox.setChecked(false);
        caramelCheckBox.setChecked(false);
        irishCreamCheckBox.setChecked(false);

        coffeeSizeSpinner.setSelection(0);
        coffeeQuantitySpinner.setSelection(0);

        coffee = new Coffee(CoffeeSize.SHORT, new boolean[]{false, false, false, false, false}, 1);
    }

    private void addToOrder() {
        Order curOrder = orderList.getCurOrder();
        if (curOrder.find(coffee) == null) {
            curOrder.addItem(coffee);
        } else {
            curOrder.find(coffee).setQuantity(curOrder.find(coffee).getQuantity() + coffee.getQuantity());
        }
        Toast.makeText(getContext(), "Added to Order", Toast.LENGTH_SHORT).show();
        Toast.makeText(getContext(), coffee.toString(), Toast.LENGTH_SHORT).show();

        reset();

    }

    @SuppressLint("SetTextI18n")
    private void updateSubtotal() {
        double subTotal = coffee.price();
        @SuppressLint("DefaultLocale") String formattedSubTotal = String.format("%.2f", subTotal);
        coffeeSubtotalTextView.setText("SubTotal: " + formattedSubTotal);
    }
}
