package rucafe.ordermanager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DonutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DonutFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private DonutAdapter adapter;

    private final OrderList orderList = OrderList.getInstance();
    private static List<Donut> selectedDonuts = new ArrayList<>();

    public DonutFragment() {
        // Required empty public constructor
    }

    public static List<Donut> getSelectedDonuts() {
        return selectedDonuts;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DonutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DonutFragment newInstance(String param1, String param2) {
        DonutFragment fragment = new DonutFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        selectedDonuts = new ArrayList<>();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_donut, container, false);

        recyclerView = view.findViewById(R.id.donut_options);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<String> donutOptions = new ArrayList<>();
        for (DonutType donutType : DonutType.values()) {
            String[] flavors = donutType.getFlavors();
            donutOptions.addAll(Arrays.asList(flavors));
        }

        adapter = new DonutAdapter(donutOptions);
        adapter.setSubtotalTextView(view.findViewById(R.id.subtotal));
        recyclerView.setAdapter(adapter);

        TextView subtotalTextView = view.findViewById(R.id.subtotal);
        Button button = view.findViewById(R.id.add_to_order);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Test", Toast.LENGTH_SHORT).show();
                Order curOrder = orderList.getCurOrder();
                for (Donut selectedDonut : selectedDonuts) {
                    if (selectedDonut.getQuantity() > 0) {
                        if (curOrder.find(selectedDonut) == null) {
                            curOrder.addItem(selectedDonut);
                        } else {
                            curOrder.find(selectedDonut).setQuantity(curOrder.find(selectedDonut).getQuantity() + selectedDonut.getQuantity());
                        }
                    }
                }
                for (MenuItem menuItem : curOrder.getItems()) {
                    Toast.makeText(getContext(), menuItem.toString(), Toast.LENGTH_SHORT).show();
                }
                selectedDonuts.clear();
                adapter.resetSpinners();
            }
        });

        return view;
    }
}
