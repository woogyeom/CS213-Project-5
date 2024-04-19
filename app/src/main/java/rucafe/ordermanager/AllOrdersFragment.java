package rucafe.ordermanager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.List;

public class AllOrdersFragment extends Fragment {

    private ListView allOrdersList;
    private Button removeOrderButton;
    private ArrayAdapter<Order> adapter;
    private int selectedPosition = -1; // -1 indicates no selection

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_orders, container, false);

        allOrdersList = view.findViewById(R.id.allOrdersList);
        removeOrderButton = view.findViewById(R.id.removeOrder);

        List<Order> orders = OrderList.getInstance().getAllOrders();
        adapter = new ArrayAdapter<Order>(
                getActivity(),
                R.layout.item_order_list,
                R.id.textViewOrderItem,
                orders) {
            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = view.findViewById(R.id.textViewOrderItem);
                Order order = getItem(position);
                if (order != null) {
                    //String displayText = "Order #" + order.getOrderNumber() + " - Total: $" + String.format("%.2f", order.getTotalAmount());
                    //textView.setText(displayText);
                    textView.setText(order.toString());
                }
                return view;
            }
        };

        allOrdersList.setAdapter(adapter);
        allOrdersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;
                allOrdersList.setItemChecked(position, true);
            }
        });

        removeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition != -1) {
                    Order orderToRemove = adapter.getItem(selectedPosition);
                    if (orderToRemove != null) {
                        OrderList.getInstance().removeOrder(orderToRemove.getOrderNumber());
                        adapter.remove(orderToRemove);
                        adapter.notifyDataSetChanged();
                        selectedPosition = -1; // Reset selection
                    }
                }
            }
        });

        return view;
    }
}
