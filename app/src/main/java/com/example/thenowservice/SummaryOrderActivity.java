package com.example.thenowservice;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.thenowservice.adapter.ProductAdapter;
import com.example.thenowservice.classes.Order;
import com.example.thenowservice.roomDatabase.AppDatabase;

public class SummaryOrderActivity extends AppCompatActivity {

    private Order order;
    private ListView listViewOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_order);

        init();
        getIntents();
        setOrderValues();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_order) {
            saveOrder();
            finishAffinity();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveOrder() {
        new createOrder().execute(this.order);
    }

    private void setOrderValues(){
        this.listViewOrders.setAdapter(new ProductAdapter(this, this.order.getProduct_list()));
    }

    private void getIntents(){
        //intent captation
        Intent intent = getIntent();
        this.order= (Order) intent.getSerializableExtra(getString(R.string.intentOrder));
    }

    private void init(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.summary_order));
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        this.listViewOrders = findViewById(R.id.order_summary_products);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed(){
        this.finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_order, menu);
        return true;
    }

    /** ASYNC TASKS **/
    /*1. Create new order*/
    class createOrder extends AsyncTask<Order, Void, Long> {

        @Override
        protected Long doInBackground(Order... orders) {
            AppDatabase database = AppDatabase.getDatabase(SummaryOrderActivity.this);
            return database.orderDao().addOrder(order);
        }
    }
}
