package com.b07project.application;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

class Order {
    String shopper;
    String brand;
    String i_name;
    // Status can one of the following: Cart (Only shopper can see) > Ordered > Complete | Canceled
    String status;
    // Get orderNumber from the database
    int orderNumber;

    // When someone makes an order (Adds something to cart)
    Order(String shopper, String brand, String i_name) {
        this.shopper = shopper;
        this.brand = brand;
        this.status = "Cart";
        this.i_name = i_name;
        this.orderNumber = 0;
    }

    // Taking information from database.
    Order(String shopper, String brand, String i_name, String status, int orderNumber) {
        this.shopper = shopper;
        this.brand = brand;
        this.status = status;
        this.i_name = i_name;
        this.orderNumber = orderNumber;
    }

    void changeStatus(User a) {
        if(status == "Canceled") {
            return;
        }
        else if (a instanceof Shopper && status == "Cart") {
            status = "Ordered";
            //orderNumber = Retriever.getNumber(this);
        }
        else if (a instanceof StoreOwner) {
            status = "Complete";
        }
        //Updater.send(this);
    }

    void cancelOrder() {
        status = "Canceled";
    }
}
