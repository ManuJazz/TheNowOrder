package com.example.thenowservice.roomDatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.thenowservice.classes.Order;

@Database(entities = {Order.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;
    private static Context myContext;

    public static AppDatabase getDatabase(Context context) {
        if (instance == null) {
            myContext = context;
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app.db").fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract OrderDao orderDao();
}
