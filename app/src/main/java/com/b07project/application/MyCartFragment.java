package com.b07project.application;

import static com.b07project.application.ShopperMain.UserEmail;
import static com.b07project.application.ShopperMain.cart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MyCartFragment extends Fragment {

    private RecyclerView ordersRecyclerView;
    //public static Cart cart;
    private String userEmail;
    View rootView;
    TextView total;
    TextView myCartTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_my_cart, container, false);
        total = rootView.findViewById(R.id.totalTxt);
        myCartTitle = rootView.findViewById(R.id.myCartTitle);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        /*if (currentUser != null)
            userEmail = currentUser.getEmail();*/
        //cart = new Cart (userEmail);
        //createSampleOrders();
        // TODO: Load recent cart from db set it to this.cart (Backend people)
        //myCartTitle.setText(userEmail);
        ordersRecyclerView = rootView.findViewById(R.id.funnyCart);
        ordersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        CartAdapter adapter = new CartAdapter(cart, this);
        ordersRecyclerView.setAdapter(adapter);
        setTotal(cart.totalPrice());
        Button purchaseButton = rootView.findViewById(R.id.purchaseButton);
        if (cart.orders.isEmpty())
            purchaseButton.setEnabled(false);
        else
        {
            purchaseButton.setOnClickListener(v -> {
                sendCart();
                clearCart();
                purchaseButton.setEnabled(false);
                purchaseButton.setOnClickListener(null);
            });
        }
        return rootView;
    }
    public void sendCart(){
        Cart temp = cart;
        //TODO: All DB saving of cart status occurs here (Backend people)
        cart.changeStatus(new Shopper(cart.shopper));
    }
    public void clearCart() {
        cart = new Cart (UserEmail);
        //TODO: Do saving here of the new cart if needed! Not sure where exactly does a new cart get created for saving (Backend People)
        CartAdapter adapter = new CartAdapter(cart, this);
        ordersRecyclerView.setAdapter(adapter);
        setTotal(cart.totalPrice());
    }
    public void setTotal(float i){
        String formattedString = String.format("%.2f", i);
        total.setText("$" + formattedString);
    }
    //for testing
    private void createSampleOrders() {
        List<Order> orders = new ArrayList<>();
        orders.add (new Order("Turtles", "Smoll", "Mike", 14.99f));
        orders.add (new Order("Turtles", "Smoll", "Mike2", 15.99f));
        cart.orders = orders;
    }
}