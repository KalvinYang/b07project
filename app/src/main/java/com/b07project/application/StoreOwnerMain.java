package com.b07project.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StoreOwnerMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner_main);
    }

    public void fromStoreOwnerMainToMain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}