package com.b07project.application;

import androidx.annotation.Keep;
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
    String brand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner_main);

        OrdersFragmentBtn = findViewById(R.id.OwnerOrders);
        StoreOwnerViewMyShopBtn = findViewById(R.id.ViewMyShopButton);
        //TODO: Hook database to fetch the brand associated with the current store owner.
        //TODO: Keep in mind Firebase accesses are Asynch so please synchronize the brand fetch before proceeding otherwise crashes will occur in later brand accesses.

        //this.brand = ;

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
    public String getBrand(){
        return this.brand;
    }
}