package com.b07project.application;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MyOrdersFragment extends Fragment {
    private RecyclerView ordersRecyclerView;
    Cart[] list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_orders, container, false);

        //TODO: Perform a database fetch for this users cart once success callback is called please create an array with each 'Ordered' or 'Canceled' Cart object
        //TODO: initialize the new orders with the information from database after array creation please call initList(orders);

        ordersRecyclerView = rootView.findViewById(R.id.ordersRecyclerView);
        ordersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //Uncomment below when the above 'to do' is completed
        //initList();
        return rootView;
    }
    //for testing
    private void createSampleOrders() {
        List<Cart> orders = new ArrayList<>();
        orders.add (new Cart ("test" ));
        orders.add (new Cart ("test2"));
        list = new Cart[orders.size()];
        list = orders.toArray(list);
    }
    public void initList ()
    {
        MyOrdersAdapter adapter = new MyOrdersAdapter(list, (ShopperMain) getActivity());
        ordersRecyclerView.setAdapter(adapter);
    }
}