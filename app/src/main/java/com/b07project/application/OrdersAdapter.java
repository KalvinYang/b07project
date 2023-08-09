package com.b07project.application;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {
    private Order[] localDataSet;

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

    public OrdersAdapter(Order[] dataSet) {
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
        StoreOwner a = new StoreOwner("foo", "bar", "Nike");
        viewHolder.getOrderID().setText(String.valueOf (localDataSet[position].price));
        viewHolder.getStatus().setText(localDataSet[position].status);
        if (localDataSet[position].status.equals("Canceled"))
        {
            viewHolder.getCanceledButton().setEnabled(false);
            viewHolder.getCanceledButton().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#808080")));
            viewHolder.getCompleteButton().setOnClickListener(v -> {
                localDataSet[position].changeStatus(a);
                viewHolder.getStatus().setText(localDataSet[position].status);
                viewHolder.getCompleteButton().setEnabled(false);
                viewHolder.getCompleteButton().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#808080")));
                notifyDataSetChanged();
                //TODO: Perform database access here to mark the localDataSet[position] order object to the 'Completed' status
                viewHolder.getCompleteButton().setOnClickListener(null);
            });
        }
        else if(localDataSet[position].status.equals("Completed"))
        {
            viewHolder.getCanceledButton().setEnabled(false);
            viewHolder.getCanceledButton().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#808080")));
            viewHolder.getCompleteButton().setEnabled(false);
            viewHolder.getCompleteButton().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#808080")));
        }
        else
        {
            viewHolder.getCanceledButton().setOnClickListener(v -> {
                localDataSet[position].changeStatus(a);
                viewHolder.getStatus().setText(localDataSet[position].status);
                viewHolder.getCanceledButton().setEnabled(false);
                viewHolder.getCanceledButton().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#808080")));
                notifyDataSetChanged();
                //TODO: Perform database access here to mark the localDataSet[position] order object to the 'Canceled' status
                viewHolder.getCanceledButton().setOnClickListener(null);
            });

            viewHolder.getCompleteButton().setOnClickListener(v -> {
                localDataSet[position].changeStatus(a);
                viewHolder.getStatus().setText(localDataSet[position].status);
                viewHolder.getCanceledButton().setEnabled(false);
                viewHolder.getCanceledButton().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#808080")));
                viewHolder.getCompleteButton().setEnabled(false);
                viewHolder.getCompleteButton().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#808080")));
                notifyDataSetChanged();
                //TODO: Perform database access here to mark the localDataSet[position] order object to the 'Completed' status
                viewHolder.getCanceledButton().setOnClickListener(null);
                viewHolder.getCompleteButton().setOnClickListener(null);
            });
        }
        viewHolder.getRoot().setOnClickListener(v -> {

        });
    }

    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
}

