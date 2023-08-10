package com.b07project.application;

import static com.b07project.application.ShopperMain.UserEmail;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyOrdersFragment extends Fragment {
    private RecyclerView ordersRecyclerView;
    ArrayList<Cart> list;
    //Cart[] list;
    String brand;

    DatabaseReference ref_order = MainActivity.db.getReference("Order");

    public static MyOrdersFragment newInstance(String brand){
        MyOrdersFragment fragment = new MyOrdersFragment();
        fragment.brand = brand;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_orders, container, false);


        ordersRecyclerView = rootView.findViewById(R.id.ordersRecyclerView);
        ordersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        ArrayList<Cart> Carts = new ArrayList<>();
        List<Integer> cart_nos = new ArrayList<>();
        MyOrdersAdapter adapter = new MyOrdersAdapter(Carts, (ShopperMain) getActivity());
        ordersRecyclerView.setAdapter(adapter);

        Query query = ref_order.orderByChild("shopper").equalTo(UserEmail);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for ( DataSnapshot orderSnap : snapshot.getChildren()){
                    int cart_no = orderSnap.child("cart_number").getValue(Integer.class);
                    int position = cart_nos.indexOf(cart_no);
                    Order order = new Order(UserEmail,
                            orderSnap.child("brand").getValue(String.class),
                            orderSnap.child("i_name").getValue(String.class),
                            orderSnap.child("status").getValue(String.class),
                            orderSnap.child("price").getValue(Float.class));

                    if ( position != -1 ) {
                        Carts.get(position).orders.add(order);
                        Carts.get(position).orderID.add(orderSnap.getKey());
                        Cart last_cart = Carts.get(Carts.size() - 1);
                        Order last_order = last_cart.orders.get(last_cart.orders.size() - 1);
                        for ( String ids: last_cart.orderID){
                            Log.i("cart_debug", "ID is: " + ids);
                        }

                        Log.i("cart_debug", "Name is: " + last_order.i_name);
                        Log.i("cart_debug", "Status is: " + last_order.status);
                        Log.i("cart_debug", "Price is: " + last_order.price);
                        Log.i("cart_debug", "Brand is: " + last_order.brand);
                        Log.i("cart_debug", "Shopper is: " + last_order.shopper);
                    } else {
                        cart_nos.add(cart_no);
                        Cart new_cart = new Cart();
                        new_cart.shopper = UserEmail;
                        new_cart.status = "Pending";
                        new_cart.orders = new ArrayList<>();
                        new_cart.orders.add(order);
                        new_cart.orderID = new ArrayList<>();
                        new_cart.orderID.add(orderSnap.getKey());
                        Carts.add(new_cart);
                        Cart last_cart = Carts.get(Carts.size() - 1);

                        for ( String ids: last_cart.orderID){
                            Log.i("cart_debug", "ID is: " + ids);
                        }
                        Log.i("cart_debug","Shopper is" + last_cart.shopper);
                        Order last_order = last_cart.orders.get(last_cart.orders.size() - 1);
                        Log.i("cart_debug", "Name is: " + last_order.i_name);
                        Log.i("cart_debug", "Status is: " + last_order.status);
                        Log.i("cart_debug", "Price is: " + last_order.price);
                        Log.i("cart_debug", "Brand is: " + last_order.brand);
                        Log.i("cart_debug", "Shopper is: " + last_order.shopper);
                    }

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //initList();
        return rootView;
    }

}