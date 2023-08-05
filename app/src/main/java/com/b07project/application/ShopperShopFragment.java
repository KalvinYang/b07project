package com.b07project.application;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShopperShopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopperShopFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    private ArrayList<String> items;
    private RecyclerView itemsRecycler;


    public ShopperShopFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ShopperShopFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShopperShopFragment newInstance(String param1) {
        ShopperShopFragment fragment = new ShopperShopFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shopper_shop, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataInitialize();

        TextView ShopViewShopName = view.findViewById(R.id.BrandName);
        ShopViewShopName.setText(mParam1);

        itemsRecycler = view.findViewById(R.id.StorePageRecyclerView);
        itemsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        itemsRecycler.setHasFixedSize(true);
        ShopperShopAdapter shopperShopAdapter = new ShopperShopAdapter(getContext(), items);
        itemsRecycler.setAdapter(shopperShopAdapter);
        shopperShopAdapter.notifyDataSetChanged();
    }

    private void dataInitialize(){
        items = new ArrayList<>();
        items.add("item1");
        items.add("item2");
        items.add("item3");
    }

}