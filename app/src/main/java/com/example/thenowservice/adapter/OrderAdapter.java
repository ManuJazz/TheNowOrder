package com.example.thenowservice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.thenowservice.R;
import com.example.thenowservice.classes.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends BaseAdapter {
    private List<Order> orderList;
    private static LayoutInflater inflater = null;
    private Context context;

    public OrderAdapter(Context context, ArrayList<Order> productList){
        this.context = context;
        this.orderList = productList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return this.orderList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.orderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null) vi = inflater.inflate(R.layout.order_row, null);

        TextView order_price = vi.findViewById(R.id.order_price);
        TextView order_table = vi.findViewById(R.id.order_table);

        order_price.setText(String.valueOf(this.orderList.get(position).getTotal_price()));
        order_table.setText(String.valueOf(this.orderList.get(position).getTable()));

        return vi;
    }
}
