package com.b07project.application;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Array;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

class Store {
    String brand;
    List<Item> items = null;

    Store(String brand) {
        this.brand = brand;
    }

    void addItem(Item i) {
        // if item list is not made yet,
        if (items == null) {
            items = new ArrayList<Item>();
        }
        items.add(i);
    }

    // To Do
    void removeItem(Item i) {
        return;
    }
}
