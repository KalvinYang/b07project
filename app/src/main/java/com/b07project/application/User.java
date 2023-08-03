package com.b07project.application;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

abstract class User {
    String username;
    private String password;

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
