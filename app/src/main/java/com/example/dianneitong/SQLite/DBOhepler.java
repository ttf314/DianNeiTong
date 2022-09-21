package com.example.dianneitong.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * 数据库操作类
 * 实现购物车的CRUD操作
 */
public class DBOhepler extends SQLiteOpenHelper {

    private static final int VERSION =1;
    private static final String DBNAME="car3.db";

    public DBOhepler(Context context){
        super(context,DBNAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表
        db.execSQL("create table tb_car(id integer ,carid integer primary key," +
                "foodname varchar(12),num integer,summoney decimal)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
