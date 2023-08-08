package com.b07project.application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShopperShopAdapter extends RecyclerView.Adapter<ShopperShopAdapter.ShopperShopHolder> {

    Context context;
    ArrayList<String> items;
    //String brand;
    private ViewShopItemClickListener viewShopItemClickListener;

    public ShopperShopAdapter(Context context, ArrayList<String> items, /*String brand,*/ ViewShopItemClickListener viewShopItemClickListener){
        this.context = context;
        this.items = items;
        //this.brand = brand;
        this.viewShopItemClickListener = viewShopItemClickListener;
    }

    @NonNull
    @Override
    public ShopperShopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.shopper_shop_layout,parent,false);

        return new ShopperShopHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopperShopHolder holder, int position) {
        String item = items.get(position);
        holder.itemName.setText(item);
        holder.viewItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewShopItemClickListener.ViewShopItemClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ShopperShopHolder extends RecyclerView.ViewHolder{
        TextView itemName;
        Button viewItemBtn, AddToCartBtn;

        public ShopperShopHolder(@NonNull View itemView){
            super(itemView);
            itemName = itemView.findViewById(R.id.ItemName);
            viewItemBtn = itemView.findViewById(R.id.ViewItemButton);
            AddToCartBtn = itemView.findViewById(R.id.AddToCartButton);
        }
    }

    public interface ViewShopItemClickListener{
        public void ViewShopItemClick(String ViewStoreItemName);
    }

}