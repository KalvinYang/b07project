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

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button loginButton;

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

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(LoginActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this, "Enter valid password", Toast.LENGTH_SHORT).show();
                    return;
                }

                loginWithEmail(email, password);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void loginWithEmail(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Query query = ref.orderByValue().equalTo(email);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            boolean isShopOwner = snapshot.getValue() != null;
                            String brand = "";
                            if(isShopOwner) {
                                brand = snapshot.getKey();
                            }
                            Navigate(isShopOwner, brand);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(LoginActivity.this, "Error logging in.",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    Toast.makeText(LoginActivity.this, "The email or password you entered is incorrect. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void Navigate(boolean isShopOwner, String brand){
        Intent intent;
        if (isShopOwner){
            intent = new Intent(LoginActivity.this, StoreOwnerMain.class);
            intent.putExtra("brand", brand);
        }
        else{
            intent = new Intent(LoginActivity.this, ShopperMain.class);
        }
        startActivity(intent);
        finish();
    }

    public void LoginToFrontpage(View view){
        Intent intent = new Intent(this,MainPage.class);
        startActivity(intent);
    }
}