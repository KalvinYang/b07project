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

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private Order[] localDataSet;
    private Cart cartInstance;
    private MyCartFragment frag;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView brand;
        private final TextView price;
        private final TextView name;
        private final Button minus;

        public ViewHolder(View view) {
            super(view);

            brand = (TextView) view.findViewById(R.id.brand);
            price = (TextView) view.findViewById(R.id.price);
            name = (TextView) view.findViewById(R.id.productName);
            minus = (Button) view.findViewById(R.id.minusButton);
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

        public Button getMinus() {
            return minus;
        }


    }

    public CartAdapter(Cart cart, MyCartFragment frag) {
        this.localDataSet = new Order[cart.orders.size()];
        this.localDataSet = cart.orders.toArray(this.localDataSet);
        this.cartInstance = cart;
        this.frag = frag;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.itemlayout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        viewHolder.getBrand().setText(localDataSet[position].brand);
        viewHolder.getName().setText(localDataSet[position].i_name);
        viewHolder.getPrice().setText("$" + String.valueOf(localDataSet[position].price));
        viewHolder.getMinus().setOnClickListener(v -> {
            cartInstance.orders.remove (position);
            //cartInstance.orderID.remove (position);
            localDataSet = new Order[cartInstance.orders.size()];
            localDataSet = cartInstance.orders.toArray(localDataSet);
            notifyDataSetChanged();
            frag.setTotal(cartInstance.totalPrice());
            //TODO: Database access and saving required here since cart has removed an item (Backend people)
        });
    }

    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
}

