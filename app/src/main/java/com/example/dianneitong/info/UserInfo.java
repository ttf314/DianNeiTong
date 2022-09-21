package com.example.dianneitong.info;

import java.io.Serializable;

/**
 * 用户信息实体类
 */
public class UserInfo implements Serializable {
    private int id;    // 用户的id
    private String uname;   // 用户名
    private String upass;   // 用户密码
    private String createDt;   // 创建时间
    private int rmb;

    public UserInfo() {
    }

    /*public UserInfo(int id) {
        this.id = id;
    }*/

    public UserInfo(int id, String uname, String upass, String createDt, int rmb) {
        this.id = id;
        this.uname = uname;
        this.upass = upass;
        this.createDt = createDt;
        this.rmb = rmb;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", uname='" + uname + '\'' +
                ", upass='" + upass + '\'' +
                ", createDt='" + createDt + '\'' +
                ", rmb=" + rmb +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpass() {
        return upass;
    }

    public void setUpass(String upass) {
        this.upass = upass;
    }

    public String getCreateDt() {
        return createDt;
    }

    public void setCreateDt(String createDt) {
        this.createDt = createDt;
    }

    public int getRmb() {
        return rmb;
    }

    public void setRmb(int rmb) {
        this.rmb = rmb;
    }
}
