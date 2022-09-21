package com.example.dianneitong.info;

import java.io.Serializable;

public class DingDanInfo implements Serializable {
    private int dingdanid;    // 订单的id
    private int id;    // 用户的id
    private String foodname;   // 食物名
    private int num;   // 食物数量
    private double price;   // 食物价格
    private  String table;//位置
    private String createDt;   // 创建时间

    public DingDanInfo(int dingdanid, int id, String foodname, int num, double price, String table, String createDt) {
        this.dingdanid = dingdanid;
        this.id = id;
        this.foodname = foodname;
        this.num = num;
        this.price = price;
        this.table = table;
        this.createDt = createDt;
    }

    public DingDanInfo() {
    }

    @Override
    public String toString() {
        return "DingDanInfo{" +
                "dingdanid=" + dingdanid +
                ", id=" + id +
                ", foodname='" + foodname + '\'' +
                ", num=" + num +
                ", price=" + price +
                ", table='" + table + '\'' +
                ", createDt='" + createDt + '\'' +
                '}';
    }


    public int getDingdanid() {
        return dingdanid;
    }

    public void setDingdanid(int dingdanid) {
        this.dingdanid = dingdanid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getCreateDt() {
        return createDt;
    }

    public void setCreateDt(String createDt) {
        this.createDt = createDt;
    }
}
