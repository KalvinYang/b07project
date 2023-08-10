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

public class ShopperMain extends AppCompatActivity {

    Button MyCartFragmentBtn, MyOrdersFragmentBtn, ShopsFragmentBtn;
    TextView shopperMainTitle;
    private Cart passer;
    private MyOrdersAdapter adapter;
    public static String UserEmail;

    public static Cart cart;
    //= getIntent().getStringExtra("UserEmail");;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopper_main);

        MyCartFragmentBtn = findViewById(R.id.MyCartButton);
        MyOrdersFragmentBtn = findViewById(R.id.OwnerOrders);
        ShopsFragmentBtn = findViewById(R.id.ShopButton);
        shopperMainTitle = findViewById(R.id.ShopperMainTitle);
        UserEmail = getIntent().getStringExtra("UserEmail");
        shopperMainTitle.setText(UserEmail);
        cart = new Cart(UserEmail);
        MyCartFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new MyCartFragment());
            }
        });

        MyOrdersFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new MyOrdersFragment());
            }
        });

        ShopsFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceShopFragment(new ShopsFragment(), UserEmail);
            }
        });

    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.ShopperFrameLayout,fragment);
        fragmentTransaction.commit();
    }

    public void replaceShopFragment(Fragment fragment, String UserEmail){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment = ShopsFragment.newInstance(UserEmail);
        fragmentTransaction.replace(R.id.ShopperFrameLayout,fragment);
        fragmentTransaction.commit();
    }

    public void fromShopperMainToMain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void setPasser (Cart c)
    {
        this.passer = c;
    }
    public Cart getPasser ()
    {
        return this.passer;
    }

    public void refreshOrderAdapter (){
        this.adapter.notifyDataSetChanged();
    }
    public void setOrderAdapter (MyOrdersAdapter adapter)
    {
        this.adapter = adapter;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ShopperMain.this, ShopperMain.class);
        UserEmail = getIntent().getStringExtra("UserEmail");
        intent.putExtra("UserEmail", UserEmail);
        Toast.makeText(ShopperMain.this, "There is no escape!",Toast.LENGTH_SHORT).show();
        startActivity(intent);
        finish();
    }
}