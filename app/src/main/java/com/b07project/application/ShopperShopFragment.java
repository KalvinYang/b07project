package com.b07project.application;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShopperShopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopperShopFragment extends Fragment implements ShopperShopAdapter.ViewShopItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private ArrayList<String> items;
    private RecyclerView itemsRecycler;

    DatabaseReference ref = MainActivity.db.getReference("Item");


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
        //dataInitialize();
        items = new ArrayList<>();

        TextView ShopViewShopName = view.findViewById(R.id.BrandName);
        Button backtoShopsBtn = view.findViewById(R.id.ShopperShopBackButton);
        ShopViewShopName.setText(mParam1);

        itemsRecycler = view.findViewById(R.id.StorePageRecyclerView);
        itemsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        itemsRecycler.setHasFixedSize(true);
        ShopperShopAdapter shopperShopAdapter = new ShopperShopAdapter(getContext(), items, mParam1, this::ViewShopItemClick);
        itemsRecycler.setAdapter(shopperShopAdapter);


        Query query = ref.orderByChild("brand").equalTo(mParam1);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if ( snapshot.exists()){
                    for ( DataSnapshot snapshot1 : snapshot.getChildren()) {
                        String key = snapshot1.getKey();
                        String item_name = snapshot1.child("name").getValue(String.class);
                        items.add(item_name);
                    }
                    shopperShopAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        backtoShopsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                ShopsFragment fragment = ShopsFragment.newInstance("");
                fr.replace(R.id.ShopperFrameLayout, fragment);
                fr.commit();
            }
        });


    }

    private void dataInitialize(){
        //Get an Array of Strings that contain the names of the items in the list;
        items = new ArrayList<>();
        items.add("item1");
        items.add("item2");
        items.add("item3");
        items.add("item4");
        items.add("item5");
        items.add("item6");
        items.add("item7");
        items.add("item8");
    }

    @Override
    public void ViewShopItemClick(String ViewStoreItemName, String brand) {
        Fragment fragment = ShopperShpItemFragment.newInstance(ViewStoreItemName, brand);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.ShopperFrameLayout, fragment, "ShopperViewShopItemView");
        transaction.addToBackStack(null);
        transaction.commit();
    }

}