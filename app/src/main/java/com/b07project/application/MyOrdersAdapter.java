package com.b07project.application;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.ViewHolder> {

    private Order[] localDataSet;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView orderNum;
        private final TextView status;
        private final View root;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            orderNum = (TextView) view.findViewById(R.id.orderNum);
            status = (TextView) view.findViewById(R.id.status);
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
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView
     */
    public MyOrdersAdapter(Order[] dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.itemorderlayout, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getOrderNum().setText(String.valueOf (localDataSet[position].price));
        viewHolder.getStatus().setText(localDataSet[position].status);
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

