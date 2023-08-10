package com.b07project.application;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class Cart extends ObjectsToSave{
    public String shopper; // shopper email to connect it to them
    String status; // Pending -> (Clicks Order) -> Ordered
    List<Order> orders; // List of all orders in this cart
    List<String> orderID;

    public int cart_number;


    Cart(String shopper){
        super(Cart.class);
        this.shopper = shopper;
        status = "Pending";
        orders = new ArrayList<>();
        orderID = new ArrayList<>();
        retrieve_current();
    }

    Cart(String shopper, String status){
        super(Cart.class);
        this.shopper = shopper;
        this.status = status;
        orderID = new ArrayList<>();
        retrieve_current();
    }

    Cart(String shopper, String status, List<Order> orders, List<String> orderID){
        this.shopper = shopper;
        this.status = status;
        this.orders = orders;
        this.orderID = orderID;
        retrieve_current();
    }

    public Cart() {
        retrieve_current();
    }



    void addOrder(Order order) {
        // initialization of cart if not initialized already
        if(orders == null){
            orders = new ArrayList<>();
        }
        orders.add(order);
    }

    void removeOrder(Order order){
        if (orders.isEmpty()){
            return;
        }
        orders.remove(order);
    }

    void retrieve_current(){
        //Retrieves Current
        DatabaseReference ref_current = MainActivity.db.getReference("Current");
        ValueEventListener listener  = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cart_number = snapshot.getValue(Integer.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        ref_current.addListenerForSingleValueEvent(listener);

        if  (listener != null){
            ref_current.removeEventListener(listener);
        }
    }

    float totalPrice(){
        float total = 0;
        for ( Order o : orders){
            total += o.price; //replace 0 by items price
        }
        return total;
    }
    void changeStatus(Shopper a) {
        if(cart_number == 0) {
            retrieve_current();
        }

        if (status.equals("Pending")) {
            status = "Ordered";
            for ( Order o : orders) {
                o.cart_number = cart_number;
                orderID.add(o.changeStatus(a));
            }
            saveObject(createHashMap());
        }

        //Updates Current
        retrieve_current();
        cart_number++;
        MainActivity.db.getReference("Current").setValue(cart_number);
        MainActivity.db.getReference("Status").setValue("damn");

    }

    @Override
    Map<String, Object> createHashMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("shopper",this.shopper);
        int item = 1;
        for (String ID : orderID) {
            map.put("Item"+item,ID);
            item++;
        }
        return map;
    }

    public void cancelCart ()
    {
        for (Order i:this.orders)
            i.cancelOrder();
        status = "Canceled";
        saveObject(createHashMap());
    }

}
