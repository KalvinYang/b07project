package com.b07project.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StoreOwnerSignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner_sign_up);
    }

    public void fromStoreOwnerToSignUp(View view){
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }

    public void fromStoreOwnerSignUpToStoreOwnerMain(View view){
        Intent intent = new Intent(this, StoreOwnerMain.class);
        startActivity(intent);
    }

}