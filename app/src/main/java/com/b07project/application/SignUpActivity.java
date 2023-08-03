package com.b07project.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void fromSignUpToMain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void fromSignUpToShopper(View view){
        Intent intent = new Intent(this,ShopperSignUpActivity.class);
        startActivity(intent);
    }

    public void fromSignUpToStore(View view){
        Intent intent = new Intent(this, StoreOwnerSignUpActivity.class);
        startActivity(intent);
    }
}