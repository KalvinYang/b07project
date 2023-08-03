package com.b07project.application;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private Item[] localDataSet;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView brand;
        private final TextView price;
        private final TextView name;
        private final TextView count;
        private final Button plus;
        private final Button minus;
        private final View root;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            brand = (TextView) view.findViewById(R.id.brand);
            price = (TextView) view.findViewById(R.id.price);
            name = (TextView) view.findViewById(R.id.productName);
            count = (TextView) view.findViewById(R.id.count);
            plus = (Button) view.findViewById(R.id.plusButton);
            minus = (Button) view.findViewById(R.id.minusButton);
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

        public View getRoot(){
            return root;
        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView
     */
    public CartAdapter(Item[] dataSet) {
        localDataSet = dataSet;
    }
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.itemlayout, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getBrand().setText(localDataSet[position].getBrand());
        viewHolder.getName().setText(localDataSet[position].getName());
        viewHolder.getPrice().setText("$" + String.valueOf(localDataSet[position].getPrice()));
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
        viewHolder.getRoot().setOnClickListener(v -> {
            //do onclick stuff in here idk order details not implement
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
}

