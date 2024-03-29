package com.example.thenowservice.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.thenowservice.R;
import com.example.thenowservice.SummaryOrderActivity;
import com.example.thenowservice.adapter.OrderAdapter;
import com.example.thenowservice.classes.Order;
import com.example.thenowservice.roomDatabase.AppDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class AllOrdersFragment extends Fragment {
    private View rootView;
    private List<Order> orderList;
    private ListView listViewOrders;
    private OrderAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.rootView =inflater.inflate(R.layout.fragment_all_orders, container, false);

        loadOrders();

        this.listViewOrders = this.rootView.findViewById(R.id.order_list);
        this.adapter = new OrderAdapter(this.getContext(), (ArrayList<Order>) this.orderList, getActivity());
        this.listViewOrders.setAdapter(this.adapter);
        //get orders from database

        return this.rootView;
    }

    private void updateOrdersList(){
        this.adapter.notifyDataSetChanged();
    }

    private void loadOrders() {
        try {
            this.orderList = new getAllOrders().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /** ASYNC TASKS **/
    /*1. Create new order*/
    class getAllOrders extends AsyncTask<Void, Void, List<Order>> {

        @Override
        protected List<Order> doInBackground(Void... voids) {
            AppDatabase database = AppDatabase.getDatabase(getActivity());
            return database.orderDao().getAllOrders();
        }
    }
}
