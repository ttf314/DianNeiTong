package com.example.dianneitong.dao;

import com.example.dianneitong.Gongju.DBHelper;
import com.example.dianneitong.info.DingDanInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单数据库操作类
 * 实现订单的CRUD操作
 */
public class DingDanDao extends DBHelper {

    // 查询所有的订单信息 R
    public List<DingDanInfo> getAllDingDanList(){
        List<DingDanInfo> list = new ArrayList<>();
        try{
            getConnection();   // 取得连接信息
            String sql = "select * from dingdaninfo";
            pStmt = conn.prepareStatement(sql);
            rs = pStmt.executeQuery();
            while(rs.next()){   // 这里原来用的是if，查询数据集合时应使用while
                DingDanInfo item = new DingDanInfo();
                item.setDingdanid(rs.getInt("dingdanid"));
                item.setId(rs.getInt("id"));
                item.setFoodname(rs.getString("foodname"));
                item.setPrice(rs.getDouble("price"));
                item.setNum(rs.getInt("num"));
                item.setTable(rs.getString("tb"));
                item.setCreateDt(rs.getString("createDt"));
                list.add(item);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return list;
    }

    /**
     * 添加订单信息 C
     * @param item 要添加的用户
     * @return int 影响的行数
     */
    public int addDingDan(DingDanInfo item){
        int iRow = 0;
        try{
            getConnection();   // 取得连接信息
            String sql = "insert into dingdaninfo(id,foodname,num,price,tb,createDt) values(?, ?, ?, ?, ?,?)";
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, item.getId());
            pStmt.setString(2, item.getFoodname());
            pStmt.setDouble(3, item.getNum());
            pStmt.setInt(4, item.getNum());
            pStmt.setString(5, item.getTable());
            pStmt.setString(6, item.getCreateDt());
            iRow = pStmt.executeUpdate();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return iRow;
    }

   /* *//**
     * 修改用户信息 U
     * @param item 要修改的用户
     * @return int 影响的行数
     *//*
    public int editFoods(DingDanInfo item){
        int iRow = 0;
        try{
            getConnection();   // 取得连接信息
            String sql = "update foods set foodname=?, money=?, num=? where foodid=?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, item.getFoodname());
            pStmt.setDouble(2, item.getMoney());
            pStmt.setInt(3, item.getNum());
            pStmt.setInt(4, item.getFoodid());
            iRow = pStmt.executeUpdate();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return iRow;
    }*/

    /**
     * 根据id 删除用户信息
     * @param id 要删除用户的id
     * @return int 影响的行数
     */
    public int delDingdan(int id){
        int iRow = 0;
        try{
            getConnection();   // 取得连接信息
            String sql = "delete from dingdaninfo where dingdanid=?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, id);
            iRow = pStmt.executeUpdate();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return iRow;
    }


}
