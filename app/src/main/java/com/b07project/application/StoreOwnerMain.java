package com.b07project.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StoreOwnerMain extends AppCompatActivity {

    Button OrdersFragmentBtn, StoreOwnerViewMyShopBtn;
    TextView storeOwner;

    public static String brandon;
    public static String StoreEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner_main);

        OrdersFragmentBtn = findViewById(R.id.OwnerOrders);
        StoreOwnerViewMyShopBtn = findViewById(R.id.ViewMyShopButton);
        storeOwner = findViewById(R.id.StoreOwnerMain);
        brandon = getIntent().getStringExtra("ninjago");
        StoreEmail = getIntent().getStringExtra("UserEmail");
        String gandum = getIntent().getStringExtra("ninjago");

        storeOwner.setText(gandum);

        OrdersFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replacetoMyOrderFragment(new OrdersFragment()/*, gandum*/);
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

    private void replacetoMyOrderFragment(Fragment fragment/*, String brand*/){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.StoreOwnerFrameLayout,fragment);
        fragmentTransaction.commit();
    }
    public void fromStoreOwnerMainToMain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(StoreOwnerMain.this, StoreOwnerMain.class);
        StoreEmail = getIntent().getStringExtra("UserEmail");
        brandon = getIntent().getStringExtra("ninjago");
        intent.putExtra("UserEmail", StoreEmail);
        intent.putExtra("ninjago", brandon);
        Toast.makeText(StoreOwnerMain.this, "There is no escape!",Toast.LENGTH_SHORT).show();
        startActivity(intent);
        finish();
    }
}