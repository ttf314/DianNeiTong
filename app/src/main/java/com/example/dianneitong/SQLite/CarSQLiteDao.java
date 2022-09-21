package com.example.dianneitong.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CarSQLiteDao {

    private DBOhepler dbOhepler;
    private SQLiteDatabase db;

    public CarSQLiteDao(Context context){
        dbOhepler=new DBOhepler(context);//初始化对象

    }
    //添加购物车
    public void  addcar(Tb_car tb_car){
        db=dbOhepler.getWritableDatabase();
        db.execSQL("insert into tb_car (id,carid,foodname,num,summoney) values (?,?,?,?,?)",
                new Object[]{tb_car.getId(),tb_car.getCarid(),tb_car.getFoodname(),
                        tb_car.getNum(),tb_car.getSummoney()});
    }
    //where id= ?
    //遍历所以信息然后添加到集合
    public List<Tb_car> getDate(int start ,int count){
        List<Tb_car> tb_car =new ArrayList<Tb_car>();
        db=dbOhepler.getWritableDatabase();
        Cursor cursor =db.rawQuery("select * from tb_car limit ?,?",
                new String[]{String.valueOf(start),String.valueOf(count)});//遍历所以信息

        //遍历所以信息然后添加到集合
        while(cursor.moveToNext()){
            tb_car.add(new Tb_car(cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getInt(cursor.getColumnIndex("carid")),
                    cursor.getString(cursor.getColumnIndex("foodname")),
                    cursor.getInt(cursor.getColumnIndex("num")),
                    cursor.getDouble(cursor.getColumnIndex("summoney"))));
        }
        return tb_car;
    }
    //遍历所以信息然后添加到集合
    public List<Tb_car> getDate2(int id,int start ,int count){
        List<Tb_car> tb_car =new ArrayList<Tb_car>();
        db=dbOhepler.getWritableDatabase();
        Cursor cursor =db.rawQuery("select * from tb_car where id= ? limit ?,?",
                new String[]{String.valueOf(id),String.valueOf(start),String.valueOf(count)});//遍历所以信息

        //遍历所以信息然后添加到集合
        while(cursor.moveToNext()){
            tb_car.add(new Tb_car(cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getInt(cursor.getColumnIndex("carid")),
                    cursor.getString(cursor.getColumnIndex("foodname")),
                    cursor.getInt(cursor.getColumnIndex("num")),
                    cursor.getDouble(cursor.getColumnIndex("summoney"))));
        }
        return tb_car;
    }

    //遍历所以信息然后添加到集合
    public List<Tb_car> getDate3(int id,int start ,int count){
        List<Tb_car> tb_car =new ArrayList<Tb_car>();
        db=dbOhepler.getWritableDatabase();
        Cursor cursor =db.rawQuery("select * from tb_car where carid= ? limit ?,?",
                new String[]{String.valueOf(id),String.valueOf(start),String.valueOf(count)});//遍历所以信息

        //遍历所以信息然后添加到集合
        while(cursor.moveToNext()){
            tb_car.add(new Tb_car(cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getInt(cursor.getColumnIndex("carid")),
                    cursor.getString(cursor.getColumnIndex("foodname")),
                    cursor.getInt(cursor.getColumnIndex("num")),
                    cursor.getDouble(cursor.getColumnIndex("summoney"))));
        }
        return tb_car;
    }

    //遍历单独用户信息然后添加到集合
   public Tb_car find(int carid){
        db=dbOhepler.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_car where carid=?",
                new String[]
                        {String.valueOf(carid)});

        if(cursor.moveToNext()){
            return new Tb_car(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getInt(cursor.getColumnIndex("carid")),
                    cursor.getString(cursor.getColumnIndex("foodname")),
                    cursor.getInt(cursor.getColumnIndex("num")),
                    cursor.getDouble(cursor.getColumnIndex("summoney")));
                   /* cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getInt(cursor.getColumnIndex("carid")),
                    cursor.getString(cursor.getColumnIndex("foodname")),
                    cursor.getInt(cursor.getColumnIndex("num")),
                    cursor.getDouble(cursor.getColumnIndex("summoney")));*/
        }
        return null;
   }
    //遍历单独用户信息然后添加到集合
    public Tb_car find2(int carid,int id){
        db=dbOhepler.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_car where carid=? and id=?",
                new String[]{String.valueOf(carid),String.valueOf(id)});
        if(cursor.moveToNext()){
            return new Tb_car(cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getInt(cursor.getColumnIndex("carid")),
                    cursor.getString(cursor.getColumnIndex("foodname")),
                    cursor.getInt(cursor.getColumnIndex("num")),
                    cursor.getDouble(cursor.getColumnIndex("summoney")));
        }
        return null;
    }

    //获取总数
    public long getCount(){
        db=dbOhepler.getWritableDatabase();

        Cursor cursor = db.rawQuery("select count(carid) from tb_car",null);
        if(cursor.moveToNext()){
            return cursor.getLong(0);

        }
        return 0;
    }


    //获取最大号
    public int getMaxId(){
        db=dbOhepler.getWritableDatabase();

        Cursor cursor = db.rawQuery("select max(carid)from tb_car",null);
        while (cursor.moveToLast()){
            return cursor.getInt(0);
        }
        return 0;
    }



}
