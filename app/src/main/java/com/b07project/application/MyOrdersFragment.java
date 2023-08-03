package com.b07project.application;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MyOrdersFragment extends Fragment {
    private RecyclerView ordersRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_orders, container, false);

        //for testing

        ordersRecyclerView = rootView.findViewById(R.id.ordersRecyclerView);
        ordersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        MyOrdersAdapter adapter = new MyOrdersAdapter(createSampleOrders());
        ordersRecyclerView.setAdapter(adapter);
        return rootView;
    }
    //for testing
    private Order[] createSampleOrders() {
        List<Order> orders = new ArrayList<>();
        //Order(String shopper, String brand, String i_name, String status, int orderNumber, int itemNumber)
        orders.add (new Order ("bob1", "mike", "shoe", "Completed", 7, 0));
        orders.add (new Order ("bob2", "chen", "sock", "Ordered", 4, 1));
        Order[] arr = new Order[orders.size()];
        return orders.toArray(arr);
    }
}