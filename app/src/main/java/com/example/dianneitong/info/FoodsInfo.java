package com.example.dianneitong.info;

import java.io.Serializable;
/**
 * 食物信息实体类
 */
public class FoodsInfo implements Serializable {
    private int foodid;    // 食物的id
    private String foodname;   // 食物名
    private double money;   // 食物价格
    private int num;   // 食物数量
    //private char image;//图


    public FoodsInfo() {

    }

    public FoodsInfo(int foodid, String foodname, double money, int num) {
        this.foodid = foodid;
        this.foodname = foodname;
        this.money = money;
        this.num = num;
    }

    public int getFoodid() {
        return foodid;
    }

    public void setFoodid(int foodid) {
        this.foodid = foodid;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "FoodsInfo{" +
                "foodid=" + foodid +
                ", foodname='" + foodname + '\'' +
                ", money=" + money +
                ", num=" + num +
                '}';
    }
}
