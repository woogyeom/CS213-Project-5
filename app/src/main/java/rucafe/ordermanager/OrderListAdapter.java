package rucafe.ordermanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderViewHolder> {

    private List<MenuItem> items;
    private int selectedItemPosition = -1;

    public OrderListAdapter(List<MenuItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        MenuItem item = items.get(position);
        holder.itemTextView.setText(item.toString());

        holder.itemView.setSelected(selectedItemPosition == position);
        holder.itemView.setOnClickListener(v -> {
            notifyItemChanged(selectedItemPosition);
            selectedItemPosition = holder.getAdapterPosition();
            notifyItemChanged(selectedItemPosition);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void removeSelectedItem() {
        if (selectedItemPosition != -1) {
            items.remove(selectedItemPosition);
            notifyItemRemoved(selectedItemPosition);
            selectedItemPosition = -1; // Reset selection
        }
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView itemTextView;

        public OrderViewHolder(View itemView) {
            super(itemView);
            itemTextView = itemView.findViewById(R.id.itemTextView); // Make sure you have a TextView with this ID in your layout.
        }
    }

    public void setItems(List<MenuItem> newItems) {
        items = newItems;
        selectedItemPosition = -1; // Reset selection
    }
}
