package com.b07project.application.MVP;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginModel {
    interface LoginListener{
        void accountError();
        void navigate(boolean owner);
    }
    public void login(String email, String password, LoginListener inter){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        //signing in
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Owner");
                    Query query = ref.orderByValue().equalTo(email);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String brand = "";
                            boolean isShopOwner = snapshot.getValue() != null;
                            if (isShopOwner) {
                                for (DataSnapshot sn : snapshot.getChildren()) {
                                    brand = sn.getKey();
                                }
                            }
                            inter.navigate(isShopOwner);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {}
                    });
                }
                else {
                    inter.accountError();
                }
            }
        });
    }
}
