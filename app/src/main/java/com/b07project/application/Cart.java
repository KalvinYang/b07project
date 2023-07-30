package com.b07project.application;

import java.sql.Array;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    List<Order> orders = null;

    void addorder(Order order){
        // initialization of cart if not initialized already
        if(orders == null){
            orders = new ArrayList<Order>();
        }
        orders.add(order);
    }

    void removeorder(Order order){
        if (orders.isEmpty()){
            return;
        }
        orders.remove(order);
    }

    //todo
    float totalprice(){
        float total = 0;
        for ( Order o : orders){
            total += 0; //replace 0 by items price
        }
        return total;
    }





}
