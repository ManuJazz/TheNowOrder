package com.example.thenowservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.thenowservice.classes.Order;
import com.example.thenowservice.classes.Product;
import com.example.thenowservice.fragments.ProductListFragment;

import java.util.ArrayList;
import java.util.List;

public class NewOrderTabbedActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private List<Product> drinks;
    private List<Product> foods;
    private List<Product> desserts;

    private ArrayList<Product> product_order;
    private Order newOrder;
    private int table_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order_tabbed);

        init();
        getIntents();
        loadProducts();
        manageTabs();
    }

    private void getIntents() {
        Intent intent = getIntent();
        this.table_number = intent.getIntExtra(getString(R.string.intentTable), 0);
    }

    public void init(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.new_order));
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        this.product_order = new ArrayList<>();
    }

    public void addProductToOrder(Product product){
        this.product_order.add(product);
        Toast.makeText(this, getString(R.string.added)+" "+product.getProduct_name(), Toast.LENGTH_LONG).show();
    }

    private void loadProducts() {
        this.drinks = new ArrayList<>();
        Product soda01 = new Product("Pepsi", (float) 2.6);
        Product soda02 = new Product("Kas", (float) 2.6);
        Product soda03 = new Product("Tónica Schweppes", (float) 2.6);
        Product soda04 = new Product("Lipton", (float) 2.6);

        this.drinks.add(soda01);
        this.drinks.add(soda02);
        this.drinks.add(soda03);
        this.drinks.add(soda04);

        this.foods = new ArrayList<>();
        Product food01 = new Product("Menú 1: pasta", (float) 7.99);
        Product food02 = new Product("Menú 2: cocido", (float) 8.99);
        Product food03 = new Product("Menú 3: pizza", (float) 9.99);
        Product food04 = new Product("Menú 4: pizza familiar", (float) 11.99);
        this.foods.add(food01);
        this.foods.add(food02);
        this.foods.add(food03);
        this.foods.add(food04);

    }

    public void manageTabs(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_order) {
            newOrder = new Order(table_number, product_order);
            Intent intent = new Intent(this, SummaryOrderActivity.class);
            intent.putExtra(getString(R.string.intentOrder), newOrder);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private String tabTitles[] = new String[]{getString(R.string.drinks), getString(R.string.foods), getString(R.string.desserts)};

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch(position){
                case 0:{//drinks
                    fragment = ProductListFragment.newInstance(drinks);
                    break;
                }

                case 1:{//foods
                    fragment = ProductListFragment.newInstance(foods);

                    break;
                }

                case 2:{//desserts
                    fragment = ProductListFragment.newInstance(drinks);

                    break;
                }
            }

            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return tabTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }
}
