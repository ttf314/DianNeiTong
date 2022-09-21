package com.example.dianneitong.SQLite;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by ZHG on 2017/01/13.
 * 从模拟服务端获取数据类
 */
public class GetSQLite {
    /*
       把省份数据封装成集合，为后面调用数据库数据替换作准备
        */
    public List<String> setnum(){
        List<String> num=new ArrayList<>();

        for(int i=1;i<100;i++){
            num.add(String.valueOf(i));
        }
        return num;
    }

    public List<String> setnum2(){
        List<String> num=new ArrayList<>();

        for(int i=1;i<25;i++){
            num.add(String.valueOf("A"+i));
            num.add(String.valueOf("B"+i));
        }
        return num;
    }
}
