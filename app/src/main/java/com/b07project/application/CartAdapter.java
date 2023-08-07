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

    private Item[] localDataSet;
    private Cart cartInstance;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView brand;
        private final TextView price;
        private final TextView name;
        private final TextView count;
        private final Button plus;
        private final Button minus;
        private final Button purchase;
        private final View root;

        public ViewHolder(View view) {
            super(view);

            brand = (TextView) view.findViewById(R.id.brand);
            price = (TextView) view.findViewById(R.id.price);
            name = (TextView) view.findViewById(R.id.productName);
            count = (TextView) view.findViewById(R.id.count);
            plus = (Button) view.findViewById(R.id.plusButton);
            minus = (Button) view.findViewById(R.id.minusButton);
            purchase = (Button) view.findViewById(R.id.purchaseButton);
            root = view;
        }

        public TextView getBrand() {
            return brand;
        }
        public TextView getPrice(){
            return price;
        }
        public TextView getCount() {
            return count;
        }
        public TextView getName(){
            return name;
        }

        public Button getMinus() {
            return minus;
        }

        public Button getPlus() {
            return plus;
        }

        public Button getPurchaseButton() { return purchase; }

        public View getRoot(){
            return root;
        }

    }

    public CartAdapter(Item[] dataSet, Cart cart) {
        localDataSet = dataSet;
        cartInstance = cart;
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
        viewHolder.getName().setText(localDataSet[position].name);
        viewHolder.getPrice().setText("$" + String.valueOf(cartInstance.totalPrice()));
        viewHolder.getMinus().setOnClickListener(v -> {
            int count = Integer.valueOf (viewHolder.getCount().getText().toString().trim());
            if (count > 0) {
                count--;
                viewHolder.getCount().setText(String.valueOf(count));
            }
            else {
                List<Item> temp = new ArrayList<>(Arrays.asList(localDataSet));
                temp.remove(localDataSet[position]);
                Item[] items = new Item[temp.size()];
                items = temp.toArray(items);
                localDataSet = items;
            }
            notifyDataSetChanged();
        });
        viewHolder.getPlus().setOnClickListener(v -> {
            int count = Integer.valueOf (viewHolder.getCount().getText().toString().trim());
            count++;
            viewHolder.getCount().setText(String.valueOf(count));
            notifyDataSetChanged();
        });
        viewHolder.getPurchaseButton().setOnClickListener(v -> {
            viewHolder.getPurchaseButton().setEnabled(false);
            viewHolder.getPurchaseButton().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#808080")));
            clearCart();
            notifyDataSetChanged();
            FragmentManager manager = ((AppCompatActivity) viewHolder.getRoot().getContext()).getSupportFragmentManager();
            Fragment shopsFragment = new ShopsFragment();
            manager.beginTransaction()
                    .replace(R.id.frameLayout3, shopsFragment)
                    .commit();
        });
        viewHolder.getRoot().setOnClickListener(v -> {
            //don't do the functionality so that when you click into the item it leads to the individual item page
        });
    }

    private void clearCart() {
        Item[] emptyCart = new Item[0];
        localDataSet = emptyCart;
    }

    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
}

