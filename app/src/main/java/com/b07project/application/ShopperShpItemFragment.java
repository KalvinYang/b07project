package com.b07project.application;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShopperShpItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopperShpItemFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    DatabaseReference ref = MainActivity.db.getReference("Item");

    public ShopperShpItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ShopperShpItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShopperShpItemFragment newInstance(String param1, String param2) {
        ShopperShpItemFragment fragment = new ShopperShpItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shopper_shp_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView ShopViewItemName = view.findViewById(R.id.shopItemNameText);
        TextView ShopViewItemBrand = view.findViewById(R.id.shopItemBrandText);
        TextView ShopViewItemDescription = view.findViewById(R.id.shopItemDecriptionText);
        TextView ShopViewItemSpecification = view.findViewById(R.id.shopItemSpecification);
        TextView ShopViewItemPrice = view.findViewById(R.id.shopItemPriceText);
        Button addToCartButton = view.findViewById(R.id.ItemAddItemToCartButton);
        Button backButton = view.findViewById(R.id.BackToShopViewButton);

        //mparam1 = name , mparam2 = brand


        Query query = ref.orderByChild("name").equalTo(mParam1);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for ( DataSnapshot snapshot1 : snapshot.getChildren()) {

                        if (mParam2.equals(snapshot1.child("brand").getValue(String.class))) {
                            ShopViewItemName.setText(mParam1);
                            ShopViewItemBrand.setText(mParam2);
                            ShopViewItemDescription.setText(snapshot1.child("description").getValue(String.class));
                            ShopViewItemSpecification.setText(snapshot1.child("specifications").getValue(String.class));
                            ShopViewItemPrice.setText(Float.toString(snapshot1.child("price").getValue(float.class)));



                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                ShopperShopFragment fragment = ShopperShopFragment.newInstance(mParam2);
                fr.replace(R.id.ShopperFrameLayout, fragment);
                fr.commit();
            }
        });
    }

}