package com.example.dianneitong.dao;

import com.example.dianneitong.Gongju.DBHelper;
import com.example.dianneitong.info.FoodsInfo;

import java.util.ArrayList;
import java.util.List;
/**
 * 用户数据库操作类
 * 实现食物的CRUD操作
 */
public class FoodsDao extends DBHelper {
    // 查询所有的食物信息 R
    public List<FoodsInfo> getAllUserList(){
        List<FoodsInfo> list = new ArrayList<>();
        try{
            getConnection();   // 取得连接信息
            String sql = "select * from foods";
            pStmt = conn.prepareStatement(sql);
            rs = pStmt.executeQuery();
            while(rs.next()){   // 这里原来用的是if，查询数据集合时应使用while
                FoodsInfo item = new FoodsInfo();
                item.setFoodid(rs.getInt("foodid"));
                item.setFoodname(rs.getString("foodname"));
                item.setMoney(rs.getDouble("money"));
                item.setNum(rs.getInt("num"));
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
     * 添加食物信息 C
     * @param item 要添加的用户
     * @return int 影响的行数
     */
    public int addFoods(FoodsInfo item){
        int iRow = 0;
        int rmb=100;
        try{
            getConnection();   // 取得连接信息
            String sql = "insert into foods(foodname, money,num) values(?, ?, ?)";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, item.getFoodname());
            pStmt.setDouble(2, item.getMoney());
            pStmt.setInt(3, item.getNum());

            iRow = pStmt.executeUpdate();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return iRow;
    }

    /**
     * 修改用户信息 U
     * @param item 要修改的用户
     * @return int 影响的行数
     */
    public int editFoods(FoodsInfo item){
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
    }

    /**
     * 根据id 删除用户信息
     * @param id 要删除用户的id
     * @return int 影响的行数
     */
    public int delUser(int id){
        int iRow = 0;
        try{
            getConnection();   // 取得连接信息
            String sql = "delete from foods where foodid=?";
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
