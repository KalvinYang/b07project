package com.b07project.application;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MyCartFragment extends Fragment {

    private RecyclerView ordersRecyclerView;
    private Cart cart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_cart, container, false);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            String userEmail = currentUser.getEmail();

        cart = new Cart(userEmail);

        ordersRecyclerView = rootView.findViewById(R.id.funnyCart);
        ordersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //CartAdapter adapter = new CartAdapter(cart.orders, cart);
        //ordersRecyclerView.setAdapter(adapter);

        Button purchaseButton = rootView.findViewById(R.id.purchaseButton);
        purchaseButton.setOnClickListener(v -> {
         //   clearCart();
        });
        return rootView;
    }

    private void clearCart() {
        Item[] emptyCart = new Item[0];
        CartAdapter adapter = new CartAdapter(emptyCart, cart);
        ordersRecyclerView.setAdapter(adapter);
    }
    //for testing
    //private Item[] createSampleOrders() {
        //List<Item> orders = new ArrayList<>();
        //Order(String shopper, String brand, String i_name, String status, int orderNumber, int itemNumber)
        //orders.add (new Item("Turtles", "Smoll", 14.99f, "Mike", "they just turtles man!"));
        //orders.add (new Item("Turtles2", "Smoll", 19.99f, "Chen", "they just better sturtles man!"));
        //Item[] arr = new Item[orders.size()];
        //return orders.toArray(arr);
    //}
}