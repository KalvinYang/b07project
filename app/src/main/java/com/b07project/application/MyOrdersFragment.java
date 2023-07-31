package com.b07project.application;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyOrdersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyOrdersFragment extends Fragment {
    private List<Order> orders;
    private RecyclerView ordersRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_orders, container, false);

        //for testing
        orders = createSampleOrders();

        ordersRecyclerView = rootView.findViewById(R.id.ordersRecyclerView);
        ordersRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        OrdersAdapter adapter = new OrdersAdapter(orders, new OrdersAdapter.OnOrderClickListener() {
            @Override
            public void onOrderClick(Order order) {
                //move to this fragment to the cart fragment but with the button being a cancel button
            }
        });
        ordersRecyclerView.setAdapter(adapter);
        return rootView;
    }
    //for testing
    private List<Order> createSampleOrders() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("", "", "", "Complete", 1));
        orders.add(new Order("", "", "", "Ordered", 2));
        return orders;
    }
}