package com.b07project.application;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Item extends ObjectsToSave{
    String name;
    String description;
    float price;
    String brand;
    String specifications;
    private String key;

    //image field may be introduced

    DatabaseReference ref = MainActivity.db.getReference("Store");

    Item(String name, String description, float price, String brand, String specifications) {
        super(Item.class);
        this.name = name;
        this.description = description;
        this.price = price;
        this.brand = brand;
        this.specifications = specifications;
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

    void updateItem(){
        updateObject(createHashMap());
    }
    void saveItem() {
        saveObject(createHashMap());
    }

    public void deleteobject(){
        this.findItem();
        ref.child(key).removeValue();
    }

    public void findItem() {
        Query query = ref.equalTo("brand",this.brand).equalTo("name",this.name);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                key = snapshot.getKey();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


    @Override
    Map<String, Object> createHashMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name",this.name);
        map.put("description",this.description);
        map.put("price",this.price);
        map.put("brand",this.brand);
        map.put("specifications",this.specifications);
        return map;
    }
}
