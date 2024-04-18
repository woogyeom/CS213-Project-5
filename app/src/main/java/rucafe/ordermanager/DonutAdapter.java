package rucafe.ordermanager;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DonutAdapter extends RecyclerView.Adapter<DonutAdapter.DonutViewHolder> {
    private final List<Donut> selectedDonuts = DonutFragment.getSelectedDonuts();

    private ArrayList<String> donutList;
    private List<Integer> quantityList;
    private TextView subtotalTextView;
    private List<Spinner> spinners;

    public DonutAdapter(ArrayList<String> donutList) {
        this.donutList = donutList;
        this.quantityList = Arrays.asList(0, 1, 2, 3, 4, 5);
        this.spinners = new ArrayList<>();
    }

    @NonNull
    @Override
    public DonutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donut, parent, false);
        return new DonutViewHolder(view, quantityList, this);
    }

    @Override
    public void onBindViewHolder(@NonNull DonutViewHolder holder, int position) {
        String donutName = donutList.get(position);
        holder.bind(donutName);
    }

    @Override
    public int getItemCount() {
        return donutList.size();
    }

    public void addDonut(String donutName, int quantity) {
        DonutType donutType = DonutTypeMapper.getDonutType(donutName);
        boolean isExisting = false;
        for (Donut donut : selectedDonuts) {
            if (donut.getFlavor().equals(donutName)) {
                donut.setQuantity(quantity);
                isExisting = true;
                break;
            }
        }
        if (!isExisting) {
            Donut selectedDonut = new Donut(donutType, donutName, quantity);
            selectedDonuts.add(selectedDonut);
        }

        updateSubtotal();
    }

    public void setSubtotalTextView(TextView textView) {
        this.subtotalTextView = textView;
    }

    public void addSpinner(Spinner spinner) {
        spinners.add(spinner);
    }

    public void resetSpinners() {
        for (Spinner spinner : spinners) {
            spinner.setSelection(0);
        }
    }

    @SuppressLint("SetTextI18n")
    public void updateSubtotal() {
        String subtotal = calculateSubtotal();
        subtotalTextView.setText("SubTotal: " + subtotal);
    }

    @SuppressLint("DefaultLocale")
    private String calculateSubtotal() {
        double subTotal = 0;
        for (Donut donut : selectedDonuts) {
            subTotal += donut.price();
        }
        return String.format("%.2f", subTotal);
    }

    public static class DonutViewHolder extends RecyclerView.ViewHolder {
        private final TextView donutNameTextView;
        private String donutName;

        public DonutViewHolder(@NonNull View itemView, List<Integer> quantityList, DonutAdapter adapter) {
            super(itemView);
            donutNameTextView = itemView.findViewById(R.id.donutName);
            Spinner quantitySpinner = itemView.findViewById(R.id.quantitySpinner);

            ArrayAdapter<Integer> spinnerAdapter = new ArrayAdapter<>(itemView.getContext(),
                    android.R.layout.simple_spinner_item, quantityList);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            quantitySpinner.setAdapter(spinnerAdapter);
            adapter.addSpinner(quantitySpinner);

            quantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    adapter.addDonut(donutName, position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }

        public void bind(String donutName) {
            this.donutName = donutName;
            donutNameTextView.setText(donutName);
        }
    }


}
