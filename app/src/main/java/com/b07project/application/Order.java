package com.b07project.application;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

class Order implements DatabaseObjects{
    String shopper;
    String brand;
    String i_name;
    int itemNumber;
    // Status can one of the following: Cart (Only shopper can see) > Ordered > Complete | Canceled
    String status;
    // Get orderNumber from the database
    int orderNumber;

    private DatabaseReference ref = MainActivity.db.getReference("order");

    // When someone makes an order (Adds something to cart)
    Order(String shopper, String brand, String i_name, int itemNumber) {
        this.shopper = shopper;
        this.brand = brand;
        this.status = "Cart";
        this.i_name = i_name;
        this.orderNumber = 0;
        this.itemNumber = itemNumber;
    }

    // Taking information from database.
    Order(String shopper, String brand, String i_name, String status, int orderNumber, int itemNumber) {
        this.shopper = shopper;
        this.brand = brand;
        this.status = status;
        this.i_name = i_name;
        this.orderNumber = orderNumber;
        this.itemNumber = itemNumber;
    }

    void changeStatus(User a) {
        if (a instanceof Shopper && status == "Cart") {
            status = "Ordered";
            updateNumber();

        }
        else if (a instanceof StoreOwner) {
            status = "Complete";
            updateObject();
        }
    }

    void cancelOrder() {
        status = "Canceled";
    }

    @Override
    public void updateNumber() {
        Query query = ref.child("current");

//        query.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                int oNum = Integer.parseInt(snapshot.getValue().toString());
//                orderNumber = oNum;
//                oNum++;
//                ref.child("current").setValue(oNum);
//                updateObject();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                return;
//            }
//        });
    }

    @Override
    public void updateObject() {
        ref.child(Integer.toString(this.orderNumber)).child("shopper").setValue(this.shopper);
        ref.child(Integer.toString(this.orderNumber)).child("brand").setValue(this.brand);
        ref.child(Integer.toString(this.orderNumber)).child("i_name").setValue(this.i_name);
        ref.child(Integer.toString(this.orderNumber)).child("status").setValue(this.status);
        ref.child(Integer.toString(this.orderNumber)).child("orderNumber").setValue(this.orderNumber);
        ref.child(Integer.toString(this.orderNumber)).child("itemNumber").setValue(this.itemNumber);
    }

    @Override
    public void deleteObject() {

    }

    public int getOrderNumber(){
        return this.orderNumber;
    }
    public String getStatus(){
        return this.status;
    }
    public void setStatus(String status) {this.status = status;}
}
