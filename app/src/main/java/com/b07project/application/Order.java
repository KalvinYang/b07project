package com.b07project.application;

import java.util.HashMap;

class Order extends ObjectsToSave{
    String shopper;
    String brand;
    String i_name;
    float price;
    // Status can one of the following: Cart (Only shopper can see) > Ordered > Complete | Canceled
    String status;

    // When someone makes an order (Adds something to cart)
    Order(String shopper, String brand, String i_name, float price) {
        super(Order.class);
        this.shopper = shopper;
        this.brand = brand;
        this.status = "Cart";
        this.price = price;
        this.i_name = i_name;
    }

    // Taking information from database.
    Order(String shopper, String brand, String i_name, String status, float price) {
        super(Order.class);
        this.shopper = shopper;
        this.brand = brand;
        this.status = status;
        this.price = price;
        this.i_name = i_name;
    }

    String changeStatus(User a) {
        if (a instanceof Shopper && status.equals("Cart")) {
            status = "Ordered";
        }
        else if (a instanceof StoreOwner) {
            status = "Complete";
        }
        return updateObject(createHashMap());
    }

    String cancelOrder() {
        status = "Canceled";
        return updateObject(createHashMap());
    }

    @Override
    HashMap<String, Object> createHashMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("shopper",this.shopper);
        map.put("brand",this.brand);
        map.put("i_name",this.i_name);
        map.put("status",this.status);
        map.put("price",this.price);
        return map;
    }
}
