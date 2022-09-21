package com.example.dianneitong.dao;


import com.example.dianneitong.Gongju.DBHelper;
import com.example.dianneitong.info.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户数据库操作类
 * 实现用户的CRUD操作
 */
public class UserDao extends DBHelper {

    // 查询所有的用户信息 R
    public List<UserInfo> getAllUserList(){
        List<UserInfo> list = new ArrayList<>();
        try{
            getConnection();   // 取得连接信息
            String sql = "select * from userinfo";
            pStmt = conn.prepareStatement(sql);
            rs = pStmt.executeQuery();
            while(rs.next()){   // 这里原来用的是if，查询数据集合时应使用while
                UserInfo item = new UserInfo();
                item.setId(rs.getInt("id"));
                item.setUname(rs.getString("uname"));
                item.setUpass(rs.getString("upass"));
                item.setCreateDt(rs.getString("createDt"));
                item.setRmb(rs.getInt("rmb"));
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
     * 按用户名和密码查询用户信息 R
     * @param uname 用户名
     * @param upass 密码
     * @return UserInfo 实例
     */
    public UserInfo getUserByUnameAndUpass(String uname, String upass){
        UserInfo item = null;
        try{
            getConnection();   // 取得连接信息
            String sql = "select * from userinfo where uname=? and upass=?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, uname);
            pStmt.setString(2, upass);
            rs = pStmt.executeQuery();
            if(rs.next()){
                item = new UserInfo();
                item.setId(rs.getInt("id"));
                item.setUname(uname);
                item.setUpass(upass);
                item.setCreateDt(rs.getString("createDt"));
                item.setId(rs.getInt("rmb"));

            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return item;
    }

    /**
     * 按用户名和密码查询用户信息 R
     * @param uname 用户名
     * @param upass 密码
     * @return UserInfo 实例
     */
    public UserInfo getUserByUnameAndUpass2(String uname, String upass){
        UserInfo item = null;
        try{
            getConnection();   // 取得连接信息
            String sql = "select id from userinfo where uname=? and upass=?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, uname);
            pStmt.setString(2, upass);
            rs = pStmt.executeQuery();
            if(rs.next()){
                item = new UserInfo();
                item.setId(rs.getInt("id"));
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return item;
    }
    /**
     * 按用户名和密码查询用户信息 R
     * @param uname 用户名
     * @param upass 密码
     * @return UserInfo 实例
     */
    public UserInfo getUserByUnameAndUpass3(String uname, String upass){
        UserInfo item = null;
        try{
            getConnection();   // 取得连接信息
            String sql = "select uname userinfo where uname=? and upass=?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, uname);
            pStmt.setString(2, upass);
            rs = pStmt.executeQuery();
            
            if(rs.next()){
                item = new UserInfo();
              /*  item.setId(rs.getInt("id"));
                item.setUname(rs.getString("uname"));*/
                //item.setUname(uname);
                item.setUname(rs.getString("uname"));
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return item;
    }

    public int getRmbByUid(String id){
        int a = 0;
        try{
            getConnection();   // 取得连接信息
            String sql = "select * from userinfo where id=?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, id);
            rs = pStmt.executeQuery();
            if(rs.next()){
                a=rs.getInt("rmb");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return a;
    }

    public ArrayList<String> getBookType(){
        UserInfo item = null;
        ArrayList<String> BookType = null;
        try{
            getConnection();   // 取得连接信息
            String sql = "select * from userinfo where id=?";
            pStmt = conn.prepareStatement(sql);
            //pStmt.setString(1, id);
            BookType = new ArrayList<String>();
            rs = pStmt.executeQuery();
            if(rs.next()){
                String ttype = rs.getString("rmb");
                BookType.add(ttype);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return BookType;
    }
    /**
     * 添加用户信息 C
     * @param item 要添加的用户
     * @return int 影响的行数
     */
    public int addUser(UserInfo item){
        int iRow = 0;
        int rmb=100;
        try{
            getConnection();   // 取得连接信息
            String sql = "insert into userinfo(uname, upass, createDt, rmb) values(?, ?, ?, ?)";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, item.getUname());
            pStmt.setString(2, item.getUpass());
            pStmt.setString(3, item.getCreateDt());
            pStmt.setInt(4, rmb);
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
    public int editUser(UserInfo item){
        int iRow = 0;
        try{
            getConnection();   // 取得连接信息
            String sql = "update userinfo set uname=?, upass=? ,rmb=? where id=?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, item.getUname());
            pStmt.setString(2, item.getUpass());
            pStmt.setInt(3, item.getRmb());
            pStmt.setInt(4, item.getId());
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
    public int editUser2(UserInfo item){
        int iRow = 0;
        try{
            getConnection();   // 取得连接信息
            String sql = "update userinfo set uname=?, upass=? ,rmb=? where id=?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, item.getUname());
            pStmt.setString(2, item.getUpass());
            pStmt.setInt(3, item.getRmb());
            pStmt.setInt(4, item.getId());
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
            String sql = "delete from userinfo where id=?";
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

    public int editUser2(String rmb,String id){
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
        return iRow;
    }
}
