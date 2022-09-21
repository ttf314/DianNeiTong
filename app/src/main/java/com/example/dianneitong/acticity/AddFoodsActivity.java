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
import com.example.dianneitong.dao.FoodsDao;
import com.example.dianneitong.info.FoodsInfo;

public class AddFoodsActivity extends AppCompatActivity {
    private EditText et_foodname, et_foodmoney,et_foodnum;

    private FoodsDao foodsDao;   // 用户数据操作类实例

    private Handler mainHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_foods);

        et_foodname=findViewById(R.id.et_foodname);
        et_foodmoney=findViewById(R.id.et_foodmoney);
        et_foodnum=findViewById(R.id.et_foodnum);

        foodsDao=new FoodsDao();
        mainHandler = new Handler(getMainLooper());

    }

    public void addfoods(View view) {

        final String foodname = et_foodname.getText().toString().trim();

        final String money = et_foodmoney.getText().toString().trim();
        double money2 = Double.valueOf(money);

        final String num = et_foodnum.getText().toString().trim();
        Integer  num2 = Integer .valueOf(num);

        if(TextUtils.isEmpty(foodname)){
            CommonUtils.showShortMsg(this, "请输入食物名称");
            et_foodname.requestFocus();
        }else if(TextUtils.isEmpty(money)){
            CommonUtils.showShortMsg(this, "请输入食物价格");
            et_foodmoney.requestFocus();
        }else if(TextUtils.isEmpty(num)){
            CommonUtils.showShortMsg(this, "请输入食物数量");
            et_foodnum.requestFocus();
        }else{
            final FoodsInfo item = new FoodsInfo();

            item.setFoodname(foodname);
            item.setMoney(money2);
            item.setNum(num2);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    final int iRow = foodsDao.addFoods(item);

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
        Intent intent = new Intent(this, FoodManegerActivity.class);
        startActivityForResult(intent, 1);
    }
}
