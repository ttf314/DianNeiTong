package com.example.dianneitong.acticity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.dianneitong.Gongju.CommonUtils;
import com.example.dianneitong.R;
import com.example.dianneitong.dao.UserDao;
import com.example.dianneitong.info.UserInfo;

public class ZhuCheActivity extends AppCompatActivity {
    private String name;

    private EditText et_uname, et_upass;

    private UserDao userDao;   // 用户数据操作类实例

    private Handler mainHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_che);

        et_uname = findViewById(R.id.et_uname);
        et_upass = findViewById(R.id.et_upass);

        userDao = new UserDao();
        mainHandler = new Handler(getMainLooper());

    }

    // 确定按钮的点击事件处理
    public void btn_ok_click(View view){

        final String uname = et_uname.getText().toString().trim();
        final String upass = et_upass.getText().toString().trim();
        UserInfo item3 = userDao.getUserByUnameAndUpass3(uname,
                upass);
        //name= String.valueOf(item3.getUname());

        if(TextUtils.isEmpty(uname)){
            CommonUtils.showShortMsg(this, "请输入用户名");
            et_uname.requestFocus();
        }else if(TextUtils.isEmpty(upass)){
            CommonUtils.showShortMsg(this, "请输入用户密码");
            et_upass.requestFocus();
        }/*else if(name.equals(uname)){
            CommonUtils.showShortMsg(this, "已经有该账户");
            et_upass.requestFocus();
        }*/else{
            final UserInfo item = new UserInfo();

            item.setUname(uname);
            item.setUpass(upass);
            item.setCreateDt(CommonUtils.getDateStrFromNow());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    final int iRow = userDao.addUser(item);

                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            setResult(1);   // 使用参数表示当前界面操作成功，并返回管理界面
                            finish();
                        }
                    });
                }
            }).start();
        }

    }


    public void btn_back(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivityForResult(intent, 1);
    }
}
