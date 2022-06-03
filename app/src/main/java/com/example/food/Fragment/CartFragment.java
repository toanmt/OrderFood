package com.example.food.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food.Database.Database;
import com.example.food.Model.Order;
import com.example.food.R;
import com.example.food.ViewHolder.CartAdapter;

import java.util.List;


public class CartFragment extends Fragment {

    RecyclerView recyclerView;
    TextView txtTotalItem, txtTotal, txtTax, txtCheckOut;
    CartAdapter adapter;

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerView = view.findViewById(R.id.recycler_cart);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        txtTotalItem = view.findViewById(R.id.totalFeeTxt);
        txtTotal = view.findViewById(R.id.totalTxt);
        txtTax = view.findViewById(R.id.taxTxt);
        txtCheckOut = view.findViewById(R.id.txtCheckOut);
        txtCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }

    @SuppressLint("Range")
    @Override
    public void onStart() {
        super.onStart();
        Database database = new Database(getContext());
        List<Order> list = database.getOrder();
        adapter = new CartAdapter(list, getContext());
        int t = 0;
        for (Order order : list) {
            t += Integer.parseInt(order.getPrice()) * Integer.parseInt(order.getQuantity());
        }
        recyclerView.setAdapter(adapter);
        txtTotalItem.setText(t + "");
        txtTax.setText(t * 0.9 + "");
        txtTotal.setText(t * 1.1 + "");
    }
}