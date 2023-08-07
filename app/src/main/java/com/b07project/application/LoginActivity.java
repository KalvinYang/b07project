package com.b07project.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.b07project.application.MVP.LoginModel;
import com.b07project.application.MVP.LoginPresenter;
import com.b07project.application.MVP.LoginView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private FirebaseAuth firebaseAuth;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button loginButton;
    private LoginPresenter presenter;

    private DatabaseReference ref = MainActivity.db.getReference("Owner");

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextTextPassword);
        loginButton = findViewById(R.id.loginbutton);
        presenter = new LoginPresenter(this, new LoginModel());
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login();
            }
        });
    }

    public void LoginToFrontpage(View view){
        Intent intent = new Intent(this,MainPage.class);
        startActivity(intent);
    }

    @Override
    public String getEmail() {
        return editTextEmail.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return editTextPassword.getText().toString().trim();
    }

    @Override
    public void moveToStoreOwner() {
        Intent intent = new Intent(LoginActivity.this, StoreOwnerMain.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void moveToShopper() {
        Intent intent = new Intent(LoginActivity.this, ShopperMain.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void makeToast(String msg) {
        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}