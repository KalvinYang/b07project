package com.b07project.application;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditItemFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    DatabaseReference ref = MainActivity.db.getReference("Item");

    private String key;

    public EditItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment EditItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditItemFragment newInstance(String param1, String param2) {
        EditItemFragment fragment = new EditItemFragment();
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



        View view = inflater.inflate(R.layout.fragment_edit_item, container, false);
        Button BacktoMyShopButton = view.findViewById(R.id.EditItemBackButton);
        Button EditShopItemButton = view.findViewById(R.id.EditItemToStoreButton);

        EditText editItemName = view.findViewById(R.id.EditItemName);
        EditText editDescription = view.findViewById(R.id.EditItemDescription);
        EditText editSpecification = view.findViewById(R.id.EditItemSpecification);
        EditText editPrice = view.findViewById(R.id.EditItemPrice);
        Item i = new Item();
        //i.findItem(mParam1,mParam2);

        Query query = ref.orderByChild("name").equalTo(mParam1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for ( DataSnapshot snapshot1 : snapshot.getChildren()) {
                        key = snapshot1.getKey();
                        if (mParam2.equals(snapshot1.child("brand").getValue(String.class))) {
                            i.name = mParam1;
                            i.brand = mParam2;
                            i.description = snapshot1.child("description").getValue(String.class);
                            i.specifications = snapshot1.child("specifications").getValue(String.class);
                            i.price = snapshot1.child("price").getValue(float.class);



                            editItemName.setText(i.name);
                            editDescription.setText(i.description);
                            editSpecification.setText(i.specifications);
                            editPrice.setText(String.valueOf(i.price));



                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });




        BacktoMyShopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                MyShopFragment fragment = MyShopFragment.newInstance(mParam2);
                fr.replace(R.id.StoreOwnerFrameLayout, fragment);
                fr.commit();
            }
        });

        EditShopItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Something to edit the store's items mparam1 = itemName, mparam2 = brand
                i.name = editItemName.getText().toString().trim();
                i.specifications = editSpecification.getText().toString().trim();
                i.description = editDescription.getText().toString().trim();
                i.price = Float.parseFloat((editPrice.getText().toString().trim()));

                i.updateItem(key);

                FragmentTransaction fr = getFragmentManager().beginTransaction();
                MyShopFragment fragment = MyShopFragment.newInstance(mParam2);
                fr.replace(R.id.StoreOwnerFrameLayout, fragment);
                fr.commit();

            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        TextView editItemTitle = view.findViewById(R.id.EditItemTitle);
        editItemTitle.setText("Edit: " + mParam1);
    }


}