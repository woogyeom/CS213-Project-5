package rucafe.ordermanager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CurrentOrderFragment extends Fragment {

    private TextView subtotalTextView;
    private TextView salesTaxTextView;
    private TextView totalAmountTextView;
    private RecyclerView orderItemsRecyclerView;
    private Button removeItemButton;
    private Button submitOrderButton;

    private OrderListAdapter adapter; // This is the adapter we just created.
    private final OrderList orderList = OrderList.getInstance();

    public CurrentOrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_order, container, false);

        subtotalTextView = view.findViewById(R.id.subtotal);
        salesTaxTextView = view.findViewById(R.id.salesTax);
        totalAmountTextView = view.findViewById(R.id.totalAmount);
        orderItemsRecyclerView = view.findViewById(R.id.itemList);
        removeItemButton = view.findViewById(R.id.removeItem);
        submitOrderButton = view.findViewById(R.id.submitOrder);

        adapter = new OrderListAdapter(orderList.getCurOrder().getItems());
        orderItemsRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        orderItemsRecyclerView.setAdapter(adapter);

        updateOrderSummary();

        removeItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.removeSelectedItem();
                updateOrderSummary(); // Update the subtotal, tax, and total after an item is removed.
            }
        });

        submitOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Submit order logic (not shown here)
                // After submission, clear the order and update UI.
                orderList.submitOrder(orderList.getCurOrder());
                adapter.notifyDataSetChanged();
                updateOrderSummary();
            }
        });

        return view;
    }


    private void updateOrderSummary() {
        // Assuming you have a method in Order to calculate subtotal, tax, and total.
        Order currentOrder = orderList.getCurOrder();
        subtotalTextView.setText(String.format("Subtotal: $%.2f", currentOrder.getSubTotal()));
        salesTaxTextView.setText(String.format("Sales Tax: $%.2f", currentOrder.getSalesTax()));
        totalAmountTextView.setText(String.format("Total: $%.2f", currentOrder.getTotalAmount()));
    }

    @Override
    public void onResume() {
        super.onResume();
        // Refresh the list in case any items have changed.
        adapter.notifyDataSetChanged();
        updateOrderSummary();
    }


}
