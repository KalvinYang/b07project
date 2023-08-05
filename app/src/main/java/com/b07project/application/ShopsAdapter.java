package com.b07project.application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShopsAdapter extends RecyclerView.Adapter<ShopsAdapter.ShopsHolder> {

    Context context;
    ArrayList<TestStore> TestStoreArr;

    private ViewShopClickListener viewShopClickListener;

    public ShopsAdapter(Context context, ArrayList<TestStore> TestStoreArr, ViewShopClickListener viewShopClickListener) {
        this.context = context;
        this.TestStoreArr = TestStoreArr;
        this.viewShopClickListener = viewShopClickListener;
    }

    @NonNull
    @Override
    public ShopsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        
        View v = LayoutInflater.from(context).inflate(R.layout.shops_row,parent,false);
        
        return new ShopsHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopsHolder holder, int position) {
        TestStore testStore = TestStoreArr.get(position);
        holder.shopRowName.setText(testStore.StoreName);
        holder.viewShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewShopClickListener.ViewShopClick(testStore.StoreName);
            }
        });
    }

    @Override
    public int getItemCount() {
        return TestStoreArr.size();
    }

    public static class ShopsHolder extends RecyclerView.ViewHolder{

        TextView shopRowName;
        Button viewShop;

        public ShopsHolder(@NonNull View itemView) {
            super(itemView);
            shopRowName = itemView.findViewById(R.id.ShopRowName);
            viewShop = itemView.findViewById(R.id.ViewShopButton);
            viewShop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }

    public interface ViewShopClickListener{
        public void ViewShopClick(String ViewStoreName);
    }

}
