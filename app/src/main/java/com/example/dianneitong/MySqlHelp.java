package com.example.dianneitong;


import com.example.dianneitong.Gongju.DBHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 直接连接数据库的辅助工具类
 */
public class MySqlHelp extends DBHelper {

    public static int getUserRmb(String id){
        //final String CLS = "com.mysql.jdbc.Driver";
        final String CLS = "com.mysql.jdbc.Driver";
        //localhost
        //final String URL = "jdbc:mysql://aaa:3306/s319ttf?characterEncoding=utf-8";
        final String URL = "jdbc:mysql://192.168.0.102:3306/s319ttf";
        final String USER = "sf";
        final String PWD = "123456";
        int count = 0;   // 用户数量
        try{
            getConnection();   // 取得连接信息
            String sql = "select rmb  from userinfo where id =?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, id);
            rs = pStmt.executeQuery();
            while(rs.next()){
                count = rs.getInt("rmb");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return count;
    }


    public void editUser2(String rmb,String id){
        int iRow = 0;
        try{
            getConnection();   // 取得连接信息
            String sql = "update userinfo set rmb=? where id=?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, rmb);
            pStmt.setString(2, id);
            iRow = pStmt.executeUpdate();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
    }
}