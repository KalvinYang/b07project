package com.b07project.application;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class Cart extends ObjectsToSave{
    String shopper; // shopper email to connect it to them
    String status; // Pending -> (Clicks Order) -> Ordered
    List<Order> orders; // List of all orders in this cart
    List<String> orderID;

    Cart(String shopper){
        super(Cart.class);
        this.shopper = shopper;
        status = "Pending";
        orders = new ArrayList<Order>();
        orderID = new ArrayList<String>();
    }

    Cart(String shopper, String status){
        super(Cart.class);
        this.shopper =shopper;
        this.status = status;
        orderID = new ArrayList<String>();
    }

    void addOrder(Order order) {
        // initialization of cart if not initialized already
        if(orders == null){
            orders = new ArrayList<Order>();
        }
        orders.add(order);
    }

    void removeOrder(Order order){
        if (orders.isEmpty()){
            return;
        }
        orders.remove(order);
    }

    float totalPrice(){
        float total = 0;
        for ( Order o : orders){
            total += o.price; //replace 0 by items price
        }
        return total;
    }
    void changeStatus(Shopper a) {
        if (status.equals("Pending")) {
            status = "Ordered";
            for ( Order o : orders) {
                orderID.add(o.changeStatus(a));
            }
        }
    }

    @Override
    Map<String, Object> createHashMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("shopper",this.shopper);
        int item = 1;
        for (String ID : orderID) {
            map.put("Item"+Integer.toString(item),ID);
            item++;
        }
        return map;
    }
}
