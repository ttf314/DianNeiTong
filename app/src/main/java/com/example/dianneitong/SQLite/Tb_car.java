package com.example.dianneitong.SQLite;

public class Tb_car {
    private int id;    // 用户id
    private int carid;    // 用户id
    private String foodname;   // 食物名
    //private double money;   // 食物价格
    private int num;   // 食物数量
    private double summoney;   // 食物总价格

    public Tb_car() {
        super();
    }

    public Tb_car(int id, int carid, String foodname, int num, double summoney) {
        super();
        this.id = id;
        this.carid = carid;
        this.foodname = foodname;
        this.num = num;
        this.summoney = summoney;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarid() {
        return carid;
    }

    public void setCarid(int carid) {
        this.carid = carid;
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

    public double getSummoney() {
        return summoney;
    }

    public void setSummoney(double summoney) {
        this.summoney = summoney;
    }


}
