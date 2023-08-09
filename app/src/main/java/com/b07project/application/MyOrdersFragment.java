package com.b07project.application;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyOrdersFragment extends Fragment {
    private RecyclerView ordersRecyclerView;
    //Cart[] list;
    ArrayList<Cart> list;
    String brand;

    public static MyOrdersFragment newInstance(String brand){
        MyOrdersFragment fragment = new MyOrdersFragment();
        fragment.brand = brand;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_orders, container, false);
        list = new ArrayList<Cart>();
        Cart testcart = new Cart("groomer@gmail.com");
        Order testOrder = new Order("groomer@gmail.com", "doomer", "Ur Mum", "Ordered", 19.99f);
        testcart.addOrder(testOrder);
        list.add(testcart);
        //list.add(new Cart("Test2"));

        //TODO: Perform a database fetch for this users cart once success callback is called please create an array with each 'Ordered' or 'Canceled' Cart object
        //TODO: initialize the new orders with the information from database after array creation please call initList(orders);

        ordersRecyclerView = rootView.findViewById(R.id.ordersRecyclerView);
        ordersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Uncomment below when the above 'to do' is completed
        //createSampleOrders();
        //initList();
        MyOrdersAdapter adapter = new MyOrdersAdapter(list, (ShopperMain) getActivity());
        ordersRecyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        return rootView;
    }
    //for testing
    //private void createSampleOrders() {
        //list = new ArrayList<Cart>();
        /*Cart cart = new Cart("test");
        cart.addOrder(new Order());
        orders.add (new Cart ("test" ));
        orders.add (new Cart ("test2"));
        //TODO fetch the list of carts and add it to orders NOTE: Email is UserEmail
        list = new Cart[orders.size()];
        list = orders.toArray(list);
    }
    public void initList ()
    {
        MyOrdersAdapter adapter = new MyOrdersAdapter(list, (ShopperMain) getActivity());
        ordersRecyclerView.setAdapter(adapter);
    }*/
}