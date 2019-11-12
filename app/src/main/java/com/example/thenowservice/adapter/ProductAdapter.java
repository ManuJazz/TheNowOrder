package com.example.thenowservice.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thenowservice.R;
import com.example.thenowservice.classes.Product;
import com.example.thenowservice.fragments.ProductListFragment;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private List<Product> productList;
    private static LayoutInflater inflater = null;
    private Context context;

    public ProductAdapter(Context context, ArrayList<Product> productList){
        this.context = context;
        this.productList = productList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return this.productList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null) vi = inflater.inflate(R.layout.product_row, null);

        TextView product_name = vi.findViewById(R.id.product_name);
        TextView product_price = vi.findViewById(R.id.product_price);
        //ImageView product_iamge = vi.findViewById(R.id.product_image);

        //product_iamge.setImageResource(R.drawable.ic_menu_gallery);
        
        product_name.setText(this.productList.get(position).getProduct_name());
        product_price.setText(String.valueOf(this.productList.get(position).getProduct_price()));

        return vi;
    }
}
