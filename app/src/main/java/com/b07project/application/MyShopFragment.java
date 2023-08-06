package com.b07project.application;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.b07project.application.databinding.FragmentFirstBinding;
import com.b07project.application.databinding.FragmentMyShopBinding;
import com.b07project.application.databinding.MyShopRowBinding;

import java.util.ArrayList;
import java.util.function.ObjIntConsumer;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyShopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyShopFragment extends Fragment implements MyShopAdapter.MyShopEditItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ArrayList<String> items;
    private RecyclerView myShopRecyclerView;
    public MyShopFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyShopFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyShopFragment newInstance(String param1, String param2) {
        MyShopFragment fragment = new MyShopFragment();
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
        View view = inflater.inflate(R.layout.fragment_my_shop, container, false);
        Button additemtomyshopbutton =view.findViewById(R.id.MyShopAddItemToStoreButton);
        additemtomyshopbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.StoreOwnerFrameLayout, new AddItemFragment());
                fr.commit();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        dataInitialize();
        myShopRecyclerView = view.findViewById(R.id.MyShopPageRecyclerView);
        myShopRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myShopRecyclerView.setHasFixedSize(true);
        MyShopAdapter myshopAdapter = new MyShopAdapter(getContext(), items, this::editItemClick);
        myShopRecyclerView.setAdapter(myshopAdapter);
        myshopAdapter.notifyDataSetChanged();
    }

    private void dataInitialize(){
        items = new ArrayList<>();
        items.add("item 1");
        items.add("item 2");
        items.add("item 3");
        items.add("item 4");
        items.add("item 5");
        items.add("item 6");
    }

    @Override
    public void editItemClick(String item){
        Fragment fragment = EditItemFragment.newInstance(item);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.StoreOwnerFrameLayout, fragment, "editMyShopItem");
        transaction.addToBackStack(null);
        transaction.commit();
    }

}