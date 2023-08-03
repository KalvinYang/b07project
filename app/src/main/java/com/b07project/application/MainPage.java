package com.b07project.application;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.b07project.application.databinding.FragmentFirstBinding;
import com.google.firebase.database.DatabaseReference;

public class MainPage extends Fragment {

    private FragmentFirstBinding binding;

    private DatabaseReference ref= MainActivity.db.getReference();
    Order o;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        o = new Order("me","brand1","itemname",200);
        Shopper a = new Shopper("bar","foo");
        String hi = o.changeStatus(a);
        ref.child("testing1").setValue(hi);

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.ToSignUpPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MainPage.this)
                        .navigate(R.id.action_FirstFragment_to_signUpActivity);
            }
        });

        binding.ToLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                NavHostFragment.findNavController(MainPage.this)
                    .navigate(R.id.action_FirstFragment_to_loginActivity);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}