package com.b07project.application;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link orderDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class orderDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public Cart c;
    public OrderDetailsAdapter adapter;
    private RecyclerView recycle;

    public orderDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment orderdetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static orderDetailsFragment newInstance(String param1, String param2) {
        orderDetailsFragment fragment = new orderDetailsFragment();
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
        View view = inflater.inflate(R.layout.fragment_orderdetails, container, false);

        c = ((ShopperMain) getActivity()).getPasser();
        Button cancelButton = view.findViewById(R.id.cancelButton);
        TextView status = view.findViewById(R.id.statusTxt);
        ImageView back = view.findViewById(R.id.backBtn);
        TextView price = view.findViewById(R.id.totalTxt);
        String formattedString = String.format("%.2f", c.totalPrice());
        price.setText("$" + formattedString);
        back.setOnClickListener(v -> {
            getActivity().onBackPressed();
        });
        recycle = view.findViewById(R.id.funnyCart);
        recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        OrderDetailsAdapter adapter = new OrderDetailsAdapter(c);
        recycle.setAdapter(adapter);

        status.setText(c.status);
        if (!c.status.equals("Canceled")) {
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    status.setText("Canceled");
                    c.cancelCart();
                    cancelButton.setVisibility(View.GONE);
                    ((ShopperMain) getActivity()).refreshOrderAdapter();
                }
            });
        } else cancelButton.setVisibility(View.GONE);
        //return view;


        return view;
    }


}