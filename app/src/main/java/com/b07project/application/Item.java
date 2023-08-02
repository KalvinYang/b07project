package com.b07project.application;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Item {
    String name;
    String description;
    float price;
    String brand;
    String specifications;
    int itemNumber;

    //image field may be introduced

    DatabaseReference ref = MainActivity.db.getReference("Store");

    Item(String name, String description, float price, String brand, String specifications, int itemNumber) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.brand = brand;
        this.specifications = specifications;
        this.itemNumber = itemNumber;
    }

    //Following methods need to reflect changes in firebase

    void Modify_name(String name){
        this.name = name;
    }

    void Modify_description(String description){
        this.description = description;
    }

    void Modify_price(float price){
        this.price = price;
    }

    void Modify_brand(String brand){
        this.brand = brand;
    }

    void Modify_specifications(String specifications){
        this.specifications = specifications;
    }

    public void updateNumber() {
        Query query = ref.child("Current");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int item_number = Integer.parseInt(snapshot.getValue().toString());
                itemNumber = item_number;
                item_number++;
                ref.child("Current").setValue(item_number);
                updateObject();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    public void updateObject() {
        ref.child(brand).child(Integer.toString(this.itemNumber)).child("name").setValue(this.name);
        ref.child(brand).child(Integer.toString(this.itemNumber)).child("description").setValue(this.description);
        ref.child(brand).child(Integer.toString(this.itemNumber)).child("price").setValue(this.price);
        ref.child(brand).child(Integer.toString(this.itemNumber)).child("brand").setValue(this.brand);
        ref.child(brand).child(Integer.toString(this.itemNumber)).child("specifications").setValue(this.specifications);
        ref.child(brand).child(Integer.toString(this.itemNumber)).child("itemNumber").setValue(this.itemNumber);
    }

    public void deleteobject(){
        ref.child(brand).child(Integer.toString(itemNumber)).removeValue();
    }



}
