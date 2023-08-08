package com.b07project.application;

import com.google.firebase.database.DatabaseReference;

class StoreOwner extends User{
    String brand;

    StoreOwner(String username, String password, String brand) {
        super(username, password);
        this.brand = brand;
    }
}
