package com.b07project.application;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.ViewHolder> {

    //private Order[] localDataSet;
    ArrayList<Order> localDataSet;
    private Cart cartInstance;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView brand;
        private final TextView price;
        private final TextView name;

        public ViewHolder(View view) {
            super(view);

            brand = (TextView) view.findViewById(R.id.brand);
            price = (TextView) view.findViewById(R.id.price);
            name = (TextView) view.findViewById(R.id.productName);
            view.findViewById(R.id.minusButton).setVisibility(View.GONE);
        }

        public TextView getBrand() {
            return brand;
        }
        public TextView getPrice(){
            return price;
        }
        public TextView getName(){
            return name;
        }


    }

    public OrderDetailsAdapter(Cart cart) {
        //this.localDataSet = new Order[cart.orders.size()];
        this.localDataSet = new ArrayList<Order>(cart.orders);
        this.cartInstance = cart;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.itemlayout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Order currentOrder = localDataSet.get(position);
        viewHolder.getBrand().setText(currentOrder.brand);
        viewHolder.getName().setText(currentOrder.i_name);
        viewHolder.getPrice().setText("$" + String.valueOf(cartInstance.totalPrice()));
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}

