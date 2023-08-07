package com.b07project.application;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.ViewHolder> {

    private Cart[] localDataSet;
    private ShopperMain s;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView orderNum;
        private final TextView status;
        private final View root;

        public ViewHolder(View view) {
            super(view);

            orderNum = (TextView) view.findViewById(R.id.orderKey);
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

    public MyOrdersAdapter(Cart[] dataSet, ShopperMain s) {
        localDataSet = dataSet;
        this.s = s;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.itemorderlayout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        viewHolder.getOrderNum().setText(localDataSet[position].orderID.toString());
        viewHolder.getStatus().setText(localDataSet[position].status);
        viewHolder.getRoot().setOnClickListener(v -> {
            s.setPasser(localDataSet[position]);
            s.setOrderAdapter(this);
            FragmentManager fragmentManager = s.getSupportFragmentManager();
            Fragment orderDetailsFragment = new orderDetailsFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, orderDetailsFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }

    @Override
    public int getItemCount() {
        return localDataSet.length;
    }

}

