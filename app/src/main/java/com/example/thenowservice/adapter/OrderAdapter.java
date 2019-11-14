package com.example.thenowservice.adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.thenowservice.MainActivity;
import com.example.thenowservice.R;
import com.example.thenowservice.classes.Order;
import com.example.thenowservice.fragments.AllOrdersFragment;
import com.example.thenowservice.roomDatabase.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends BaseAdapter {
    private List<Order> orderList;
    private static LayoutInflater inflater = null;
    private Context context;
    private MainActivity fragmentActivity;

    public OrderAdapter(Context context, ArrayList<Order> productList, FragmentActivity fragmentActivity){
        this.context = context;
        this.orderList = productList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.fragmentActivity = (MainActivity) fragmentActivity;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null) vi = inflater.inflate(R.layout.order_row, null);

        Button pay_button = vi.findViewById(R.id.pay_button);
        if(orderList.get(position).isPaid()){
            pay_button.setVisibility(View.GONE);
            LinearLayout linearLayout = vi.findViewById(R.id.order_row);
            linearLayout.setBackgroundColor(this.context.getColor(R.color.red));
        }

        TextView order_price = vi.findViewById(R.id.order_price);
        TextView order_table = vi.findViewById(R.id.order_table);

        order_price.setText(String.valueOf(this.orderList.get(position).getTotal_price()));
        order_table.setText(String.valueOf(this.orderList.get(position).getTable()));

        pay_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pay_order(orderList.get(position));
            }
        });

        return vi;
    }

    private void pay_order(Order order_to_pay) {
        order_to_pay.setPaid(true);
        updateOrder(order_to_pay);
    }

    private void updateOrder(Order order_to_pay) {
        new updateOrder().execute(order_to_pay);
        this.fragmentActivity.loadDefaultFragment();
    }

    /** ASYNC TASKS **/
    /*1. Create new order*/
    class updateOrder extends AsyncTask<Order, Void, Integer> {

        @Override
        protected Integer doInBackground(Order... orders) {
            AppDatabase database = AppDatabase.getDatabase(OrderAdapter.this.context);
            return database.orderDao().updateOrder(orders[0]);
        }
    }

}
