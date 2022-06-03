package com.example.food.Database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.food.Model.Order;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private  Context context;
    private static final String DB_NAME = "OrderFood.db";
    private static  final int DB_VERSION = 1;

    public Database(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    public void QueryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
        database.close();
    }

    public Cursor GetData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(sql, null);
        database.close();
        return cursor;
    }

    @SuppressLint("Range")
    public List<Order> getOrder(){
        SQLiteDatabase database = getReadableDatabase();
        Cursor c = database.rawQuery("SELECT * FROM OrderDetail", null);
        List<Order> list = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                list.add(new Order(c.getString(c.getColumnIndex("ProductId")),
                        c.getString(c.getColumnIndex("ProductName")),
                        c.getString(c.getColumnIndex("Quantity")),
                        c.getString(c.getColumnIndex("Price")),
                        c.getString(c.getColumnIndex("Discount")),
                        c.getString(c.getColumnIndex("Image"))
                ));
            }
            while (c.moveToNext());
        }

        return list;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
