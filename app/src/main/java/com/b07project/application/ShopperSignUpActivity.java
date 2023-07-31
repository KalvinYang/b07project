package com.b07project.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ShopperSignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopper_sign_up);
    }

    public void fromShopperToSignUp(View view){
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }

    public void fromShopperSignUpToShopperMain(View view){
        Intent intent = new Intent(this,ShopperMain.class);
        startActivity(intent);
    }
}