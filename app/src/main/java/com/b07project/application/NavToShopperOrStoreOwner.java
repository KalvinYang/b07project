package com.b07project.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NavToShopperOrStoreOwner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_to_shopper_or_store_owner);
    }

    public void toShopperMain(View view){
        Intent intent = new Intent(this, ShopperMain.class);
        startActivity(intent);
    }

    public void toStoreOwnerMain(View view){
        Intent intent = new Intent(this, StoreOwnerMain.class);
        startActivity(intent);
    }
}