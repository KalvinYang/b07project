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

public class MyShopAdapter extends RecyclerView.Adapter<MyShopAdapter.MyShopHolder>{

    Context context;
    ArrayList<String> Myitems;


    public MyShopAdapter(Context context, ArrayList<String> Myitems){
        this.context = context;
        this.Myitems = Myitems;
    }

    @NonNull
    @Override
    public MyShopAdapter.MyShopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.my_shop_row,parent,false);
        return new MyShopHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyShopAdapter.MyShopHolder holder, int position) {
        String item = Myitems.get(position);
        holder.MyItem.setText(item);
    }

    @Override
    public int getItemCount() {
        return Myitems.size();
    }

    public static class MyShopHolder extends RecyclerView.ViewHolder{
        TextView MyItem;
        Button EditMyItemBtn, RemoveItemBtn;

        public MyShopHolder(@NonNull View itemView){
            super(itemView);
            MyItem = itemView.findViewById(R.id.MyItemName);
            EditMyItemBtn = itemView.findViewById(R.id.EditItemButton);
            RemoveItemBtn = itemView.findViewById(R.id.RemoveItemButton);
        }
    }
}
