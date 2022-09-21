package com.example.dianneitong.Gongju;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 直接连接数据库的辅助工具类
 */
public class MySqlHelp {
    public static int getUserSize(){
        //final String CLS = "com.mysql.jdbc.Driver";
        final String CLS = "com.mysql.jdbc.Driver";
        //localhost
        //final String URL = "jdbc:mysql://aaa:3306/s319ttf?characterEncoding=utf-8";
        //192.168.43.110
        final String URL = "jdbc:mysql://192.168.43.110:3306/s319ttf?characterEncoding=utf-8";
        //final String URL = "jdbc:mysql://192.168.0.109:3306/s319ttf?characterEncoding=utf-8";
        final String USER = "sf";
        final String PWD = "123456";

        int count = 0;   // 用户数量

        try{
            Class.forName(CLS);
            Connection conn = DriverManager.getConnection(URL, USER, PWD);
            String sql = "select count(1) as sl from userinfo";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);//访问数据
            while(rs.next()){
                count = rs.getInt("sl");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return count;
    }
}