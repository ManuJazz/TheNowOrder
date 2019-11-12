package com.example.thenowservice.roomDatabase;

import android.arch.persistence.room.TypeConverter;
import com.example.thenowservice.classes.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.lang.reflect.Type;

public class Converters {
    @TypeConverter
    public static ArrayList<Product> string2product(String string_product){
        Type listType = new TypeToken<ArrayList<Product>>() {}.getType();
        return new Gson().fromJson(string_product, listType);
    }

    @TypeConverter
    public static String product2string(ArrayList<Product> products){
        Gson gson = new Gson();
        String json = gson.toJson(products);
        return json;
    }
}
