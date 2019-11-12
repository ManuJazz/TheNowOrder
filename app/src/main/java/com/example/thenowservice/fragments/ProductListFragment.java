package com.example.thenowservice.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.thenowservice.NewOrderTabbedActivity;
import com.example.thenowservice.R;
import com.example.thenowservice.adapter.ProductAdapter;
import com.example.thenowservice.classes.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductListFragment extends Fragment {

    private static final String PRODUCT_LIST = "productList";
    private ArrayList<Product> productList;
    private View rootView;
    private ListView product_list;

    // TODO: Rename and change types and number of parameters
    public static ProductListFragment newInstance(List<Product> products) {
        ProductListFragment fragment = new ProductListFragment();
        Bundle args = new Bundle();
        args.putSerializable(PRODUCT_LIST, (Serializable) products);
        fragment.setArguments(args);
        return fragment;
    }

    public void manageClicks(){
        this.product_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewOrderTabbedActivity activity = (NewOrderTabbedActivity) getActivity();
                activity.addProductToOrder(productList.get(position));
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.rootView =inflater.inflate(R.layout.fragment_product_list, container, false);

        this.productList = (ArrayList<Product>) getArguments().getSerializable(PRODUCT_LIST);

        this.product_list = this.rootView.findViewById(R.id.product_list);
        this.product_list.setAdapter(new ProductAdapter(this.getContext(), this.productList));

        manageClicks();

        return this.rootView;
    }

}
