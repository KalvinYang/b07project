package com.b07project.application;

import static com.b07project.application.StoreOwnerMain.StoreEmail;
import static com.b07project.application.StoreOwnerMain.brandon;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {
    //private Order[] localDataSet;
    ArrayList<Order> localDataSet;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView orderID;
        private final TextView status;
        private final Button canceledButton;
        private final Button completeButton;
        private final View root;

        public ViewHolder(View view) {
            super(view);
            orderID = (TextView) view.findViewById(R.id.orderKey);
            status = (TextView) view.findViewById(R.id.status);
            completeButton = (Button) view.findViewById(R.id.completedButton);
            canceledButton = (Button) view.findViewById(R.id.canceledButton);
            root = view;
        }

        public TextView getStatus() {
            return status;
        }
        public TextView getOrderID(){
            return orderID;
        } // no order num using order key
        public View getRoot(){
            return root;
        }
        public Button getCompleteButton(){
            return completeButton;
        }
        public Button getCanceledButton(){
            return canceledButton;
        }
    }

    public OrdersAdapter(ArrayList<Order> dataSet) {
        localDataSet = dataSet;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.itemorderstoreownerlayout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        StoreOwner a = new StoreOwner(StoreEmail,  brandon);
        Order current = localDataSet.get(position);

        viewHolder.getOrderID().setText(String.valueOf (current.price));
        viewHolder.getStatus().setText(current.status);
        if (current.status.equals("Canceled"))
        {
            viewHolder.getCanceledButton().setEnabled(false);
            viewHolder.getCanceledButton().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#808080")));
            viewHolder.getCompleteButton().setOnClickListener(v -> {
                viewHolder.getStatus().setText(current.status);
                viewHolder.getCompleteButton().setEnabled(false);
                viewHolder.getCompleteButton().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#808080")));
                viewHolder.getCompleteButton().setOnClickListener(null);
            });
        }
        else if(current.status.equals("Completed"))
        {
            viewHolder.getCanceledButton().setEnabled(false);
            viewHolder.getCanceledButton().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#808080")));
            viewHolder.getCompleteButton().setEnabled(false);
            viewHolder.getCompleteButton().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#808080")));
        }
        else if(current.status.equals("Ordered"))
        {
            viewHolder.getCanceledButton().setOnClickListener(v -> {
                current.cancelOrder();
                viewHolder.getStatus().setText(current.status);
                viewHolder.getCanceledButton().setEnabled(false);
                viewHolder.getCanceledButton().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#808080")));
                viewHolder.getCanceledButton().setOnClickListener(null);
                updateOrderStat(current);
            });

            viewHolder.getCompleteButton().setOnClickListener(v -> {
                current.changeStatus(a);
                viewHolder.getStatus().setText(current.status);
                viewHolder.getCanceledButton().setEnabled(false);
                viewHolder.getCanceledButton().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#808080")));
                viewHolder.getCompleteButton().setEnabled(false);
                viewHolder.getCompleteButton().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#808080")));
                notifyDataSetChanged();
                viewHolder.getCanceledButton().setOnClickListener(null);
                viewHolder.getCompleteButton().setOnClickListener(null);
                updateOrderStat(current);
            });

            viewHolder.getRoot().setOnClickListener(v -> {

            });
        }
    }

    void updateOrderStat(Order current){
        DatabaseReference ref = MainActivity.db.getReference("Order");
        Query query = ref.orderByChild("shopper").equalTo(current.shopper);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot sn : snapshot.getChildren()) {
                    if (sn.child("brand").getValue().equals(current.brand) && sn.child("price").getValue(Float.class) == current.price && sn.child("i_name").getValue().equals(current.i_name)) {
                        String key = sn.getKey();
                        current.updateObject(key, current.createHashMap());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}

