package com.example.dianneitong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dianneitong.Gongju.CommonUtils;
import com.example.dianneitong.dao.UserDao;
import com.example.dianneitong.info.UserInfo;

public class UseEditActivity extends AppCompatActivity {
    private EditText et_uname, et_upass,et_rmb;
    private TextView tv_createDt;
    private UserDao userDao;   // 用户数据操作类实例
    private UserInfo userEdit;   // 当前要修改的用户信息

    private Handler mainHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_edit);

        et_uname = findViewById(R.id.et_uname);
        et_upass = findViewById(R.id.et_upass);
        et_rmb = findViewById(R.id.et_rmb);
        tv_createDt = findViewById(R.id.tv_createDt);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            userEdit = (UserInfo) bundle.getSerializable("userEdit");

            et_uname.setText(userEdit.getUname());
            et_upass.setText(userEdit.getUpass());
            et_rmb.setText(userEdit.getRmb()+"");
            //tv_createDt.setText(userEdit.getCreateDt());
        }

        userDao = new UserDao();
        mainHandler = new Handler(getMainLooper());

    }
    // 确定按钮的点击事件处理
    public void btn_ok_click(View view){
        final String uname = et_uname.getText().toString().trim();
        final String upass = et_upass.getText().toString().trim();
        final String urmb = et_rmb.getText().toString().trim();

        if(TextUtils.isEmpty(uname)){
            CommonUtils.showShortMsg(this, "请输入用户名");
            et_uname.requestFocus();
        }else if(TextUtils.isEmpty(upass)){
            CommonUtils.showShortMsg(this, "请输入用户密码");
            et_upass.requestFocus();
        }else if(TextUtils.isEmpty(urmb)){
            CommonUtils.showShortMsg(this, "请输入余额");
            et_upass.requestFocus();
        }else{
            //int a;
            //a=Integer.getInteger(urmb);
            userEdit.setUname(uname);
                    userEdit.setUpass(upass);
                    userEdit.setRmb(Integer.valueOf(urmb));
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            final int iRow = userDao.editUser(userEdit);

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
}
