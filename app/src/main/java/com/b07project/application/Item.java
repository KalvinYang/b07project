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
    Double price;
    String brand;
    String specifications;
    private String key;

    //image field may be introduced

    DatabaseReference ref = MainActivity.db.getReference("Item");

    Item(){

    }

    Item(String name, String description, Double price, String brand, String specifications) {
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

    void Modify_price(Double price){
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
        //this.findItem();
        ref.child(key).removeValue();
    }

    public void findItem(String name, String brand) {

        Query query = ref.orderByChild("brand").equalTo(brand);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for ( DataSnapshot snapshot1 : snapshot.getChildren()) {
                        if (name.equals(snapshot1.child("name").getValue(String.class))) {
                            MainActivity.db.getReference().child("Status").setValue(snapshot1.child("description").getValue(String.class));
                            Item.this.name = name;
                            Item.this.brand = brand;
                            Item.this.description = snapshot1.child("description").getValue(String.class);
                            Item.this.specifications = snapshot1.child("specifications").getValue(String.class);
                            Item.this.price = snapshot1.child("price").getValue(Double.class);
                        }
                    }
                }
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
