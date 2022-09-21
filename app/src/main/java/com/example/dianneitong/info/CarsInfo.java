package com.example.dianneitong.info;

import java.io.Serializable;
/**
 * 购物车信息实体类
 */
public class CarsInfo implements Serializable {
    private int carid;    // 食物的id
    private int id;    // 用户id
    private String foodname;   // 食物名
    private double money;   // 食物价格
    private int num;   // 食物数量

    public CarsInfo() {
    }

    public CarsInfo(int carid, int id, String foodname, double money, int num) {
        this.carid = carid;
        this.id = id;
        this.foodname = foodname;
        this.money = money;
        this.num = num;
    }

    @Override
    public String toString() {
        return "CarsInfo{" +
                "carid=" + carid +
                ", id=" + id +
                ", foodname='" + foodname + '\'' +
                ", money=" + money +
                ", num=" + num +
                '}';
    }

    public int getCarid() {
        return carid;
    }

    public void setCarid(int carid) {
        this.carid = carid;
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
}
