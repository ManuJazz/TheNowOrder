package com.example.thenowservice.roomDatabase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.widget.ListView;

import com.example.thenowservice.classes.Order;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface OrderDao {
    @Insert
    public long addOrder(Order order);

    @Update
    public int updateOrder(Order order);

    @Delete
    public int deleteOrder(Order order);

    @Query("SELECT * FROM `Order` WHERE id_order = :order_id")
    public Order getById(long order_id);

    @Query("SELECT * FROM `Order`")
    public List<Order> getAllOrders();
}
