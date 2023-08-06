package com.b07project.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StoreOwnerMain extends AppCompatActivity {

    Button OrdersFragmentBtn, StoreOwnerViewMyShopBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner_main);

        OrdersFragmentBtn = findViewById(R.id.OwnerOrders);
        StoreOwnerViewMyShopBtn = findViewById(R.id.ViewMyShopButton);

        OrdersFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new OrdersFragment());
            }
        });

        StoreOwnerViewMyShopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new MyShopFragment());
            }
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.StoreOwnerFrameLayout,fragment);
        fragmentTransaction.commit();
    }

}