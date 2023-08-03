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

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView orderNum;
        private final TextView status;
        private final Button pendingButton;
        private final Button completeButton;
        private final View root;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            orderNum = (TextView) view.findViewById(R.id.orderNum);
            status = (TextView) view.findViewById(R.id.status);
            completeButton = (Button) view.findViewById(R.id.completedButton);
            pendingButton = (Button) view.findViewById(R.id.pendingButton);
            root = view;
        }

        public TextView getStatus() {
            return status;
        }
        public TextView getOrderNum(){
            return orderNum;
        }
        public View getRoot(){
            return root;
        }
        public Button getCompleteButton(){
            return completeButton;
        }
        public Button getPendingButton(){
            return pendingButton;
        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView
     */
    public OrdersAdapter(Order[] dataSet) {
        localDataSet = dataSet;
    }
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.itemorderstoreownerlayout, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getOrderNum().setText(String.valueOf (localDataSet[position].getOrderNumber()));
        viewHolder.getStatus().setText(localDataSet[position].getStatus());
        if (localDataSet[position].getStatus().equals("Pending"))
        {
            viewHolder.getPendingButton().setEnabled(false);
            viewHolder.getPendingButton().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#808080")));
            viewHolder.getCompleteButton().setOnClickListener(v -> {
                localDataSet[position].setStatus("Completed");
                viewHolder.getStatus().setText(localDataSet[position].getStatus());
                viewHolder.getCompleteButton().setEnabled(false);
                viewHolder.getCompleteButton().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#808080")));
                notifyDataSetChanged();
                viewHolder.getCompleteButton().setOnClickListener(null);
            });
        }
        else if(localDataSet[position].getStatus().equals("Completed"))
        {
            viewHolder.getPendingButton().setEnabled(false);
            viewHolder.getPendingButton().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#808080")));
            viewHolder.getCompleteButton().setEnabled(false);
            viewHolder.getCompleteButton().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#808080")));
        }
        else
        {
            viewHolder.getPendingButton().setOnClickListener(v -> {
                localDataSet[position].setStatus("Pending");
                viewHolder.getStatus().setText(localDataSet[position].getStatus());
                viewHolder.getPendingButton().setEnabled(false);
                viewHolder.getPendingButton().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#808080")));
                notifyDataSetChanged();
                viewHolder.getPendingButton().setOnClickListener(null);
            });

            viewHolder.getCompleteButton().setOnClickListener(v -> {
                localDataSet[position].setStatus("Completed");
                viewHolder.getStatus().setText(localDataSet[position].getStatus());
                viewHolder.getPendingButton().setEnabled(false);
                viewHolder.getPendingButton().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#808080")));
                viewHolder.getCompleteButton().setEnabled(false);
                viewHolder.getCompleteButton().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#808080")));
                notifyDataSetChanged();
                viewHolder.getPendingButton().setOnClickListener(null);
                viewHolder.getCompleteButton().setOnClickListener(null);
            });
        }
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

