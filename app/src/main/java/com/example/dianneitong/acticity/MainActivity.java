package com.example.dianneitong.acticity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dianneitong.Gongju.CommonUtils;
import com.example.dianneitong.Gongju.MySqlHelp;
import com.example.dianneitong.R;
import com.example.dianneitong.dao.UserDao;
import com.example.dianneitong.info.UserInfo;

/**
 * 主界面业务逻辑代码
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String uid;

    private Button btn_query_count, btn_login,btn_zuche;

    private TextView tv_user_count;   // 用户数量文本框

    private EditText et_uname, et_upass;  // 用户名、密码框

    private UserDao dao;   // 用户数据库操作类
    private Handler mainHandler ;     // 主线程

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            //super.handleMessage(msg);
            if(msg.what==0){
                int count = (Integer)msg.obj;
                tv_user_count.setText("数据库中的用户数量为："+count);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        //btn_query_count = findViewById(R.id.btn_query_count);
        //tv_user_count = findViewById(R.id.tv_user_count);
        //btn_login=findViewById(R.id.btn_login);

        et_uname = findViewById(R.id.et_uname);
        et_upass = findViewById(R.id.et_upass);

        btn_login = findViewById(R.id.btn_login);
        btn_zuche=findViewById(R.id.btn_zuche);
        dao = new UserDao();
        mainHandler = new Handler(getMainLooper());   // 获取主线程

        //btn_query_count.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        btn_zuche.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
           /* case R.id.btn_query_count:   // 查询数量
                doQueryCount();
                break;*/

            case R.id.btn_login:    // 登录按钮

                try
                {
                    doLogin();
                }
                catch (Exception e)
                {
                    CommonUtils.showShortMsg(this, "错误！！！");
                    throw e;
                }

                //doLogin();
                break;

            case R.id.btn_zuche:    // 注册跳转按钮
                Intent intent = new Intent(this, ZhuCheActivity.class);
                startActivityForResult(intent, 1);
                break;
        }
    }

    // 执行查询用户数量的方法
    private void doQueryCount(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                int count = MySqlHelp.getUserSize();
                Message msg = Message.obtain();
                msg.what = 0;   // 查询结果
                msg.obj = count;
                // 向主线程发送数据
                handler.sendMessage(msg);
            }
        }).start();
    }

    // 执行登录操作
    private void doLogin(){
        final String uname = et_uname.getText().toString().trim();
        final String upass = et_upass.getText().toString().trim();

        if(TextUtils.isEmpty(uname)){
            CommonUtils.showShortMsg(this, "请输入用户名");
            et_uname.requestFocus();
        }else if(TextUtils.isEmpty(upass)){
            CommonUtils.showShortMsg(this, "请输入用户密码");
            et_upass.requestFocus();
        }else if(uname.equals("BOSS")&&upass.equals("BOSS")){
            CommonUtils.showShortMsg(this, "请输入用户密码");
            Intent intent = new Intent(MainActivity.this, ManerGerBossActivity.class);
            startActivity(intent);
        }else{
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final UserInfo item = dao.getUserByUnameAndUpass(uname, upass);
                    final UserInfo item2 = dao.getUserByUnameAndUpass2(uname, upass);
                    uid= String.valueOf(item2.getId());
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(item == null){
                                CommonUtils.showDlgMsg(MainActivity.this, "用户名或密码错误");
                            }else {
                                //CommonUtils.showDlgMsg(MainActivity.this, "登录成功，进入用户管理");
                                CommonUtils.showLonMsg(MainActivity.this, "登录成功，进入用户管理");
                                // 调用用户管理界面
                                Intent intent = new Intent(MainActivity.this, YongHuMainActivity.class);
                                intent.putExtra("userid",uid);
                                startActivity(intent);
                                //startActivityForResult(intent,1);
                            }
                        }
                    });
                }
            }).start();

        }

    }
}
