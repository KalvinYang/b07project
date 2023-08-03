package com.b07project.application;

import com.google.firebase.database.DatabaseReference;

class StoreOwner extends User{
    String brand;
    private DatabaseReference ref = MainActivity.db.getReference("Owner");

    StoreOwner(String username, String password, String brand) {
        super(username, password);
        this.brand = brand;
    }

    void SaveToDb(){
        ref.child(username).setValue(brand);
    }
}
