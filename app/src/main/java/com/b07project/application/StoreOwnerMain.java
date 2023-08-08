package com.b07project.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StoreOwnerMain extends AppCompatActivity {

    Button OrdersFragmentBtn, StoreOwnerViewMyShopBtn;
    TextView storeOwner;

    public static String brandon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner_main);

        OrdersFragmentBtn = findViewById(R.id.OwnerOrders);
        StoreOwnerViewMyShopBtn = findViewById(R.id.ViewMyShopButton);
        storeOwner = findViewById(R.id.StoreOwnerMain);
        brandon = getIntent().getStringExtra("ninjago");
        String gandum = getIntent().getStringExtra("ninjago");

        storeOwner.setText(gandum);

        OrdersFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replacetoMyOrderFragment(new OrdersFragment(), gandum);
            }
        });

        StoreOwnerViewMyShopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replacetoMyShopFragment(new MyShopFragment(), gandum);
            }
        });
    }

    private void replacetoMyShopFragment(Fragment fragment, String brand){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment = MyShopFragment.newInstance(brand);
        fragmentTransaction.replace(R.id.StoreOwnerFrameLayout,fragment);
        fragmentTransaction.commit();
    }

    private void replacetoMyOrderFragment(Fragment fragment, String brand){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment = OrdersFragment.newInstance(brand);
        fragmentTransaction.replace(R.id.StoreOwnerFrameLayout,fragment);
        fragmentTransaction.commit();
    }

}