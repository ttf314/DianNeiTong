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

import java.util.ArrayList;
import java.util.List;

public class FoodsEditActivity extends AppCompatActivity {
    private EditText et_foodname2, et_foodmoney2,et_foodnum2;
    private FoodsDao foodsDao;   // 食物数据操作类实例
    private Handler mainHandler;
    private FoodsInfo foodsInfoEdit;   // 当前要修改的用户信息



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods_edit);

        et_foodname2=findViewById(R.id.et_foodname2);
        et_foodmoney2=findViewById(R.id.et_money2);
        et_foodnum2=findViewById(R.id.et_num2);

        foodsDao=new FoodsDao();
        mainHandler = new Handler(getMainLooper());

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            foodsInfoEdit = (FoodsInfo) bundle.getSerializable("userEdit");
            //类型转换
            //viewHolder.tv_money.setText(String.valueOf(item.getMoney()));
           // viewHolder.tv_num.setText(item.getNum()+"");
            et_foodname2.setText(foodsInfoEdit.getFoodname());
            et_foodmoney2.setText(String.valueOf(foodsInfoEdit.getMoney()));
            et_foodnum2.setText(foodsInfoEdit.getNum()+"");
            //tv_createDt.setText(userEdit.getCreateDt());
        }

        foodsDao = new FoodsDao();
        mainHandler = new Handler(getMainLooper());

    }

    public void btn_ok_click(View view) {
        final String et_foodname3 = et_foodname2.getText().toString().trim();
        final String et_foodmoney3 = et_foodmoney2.getText().toString().trim();
        final String et_foodnum3 = et_foodnum2.getText().toString().trim();
        if(TextUtils.isEmpty(et_foodname3)){
            CommonUtils.showShortMsg(this, "请输入食物名称");
            et_foodname2.requestFocus();
        }else if(TextUtils.isEmpty(et_foodmoney3)){
            CommonUtils.showShortMsg(this, "请输入食物价格");
            et_foodmoney2.requestFocus();
        }else if(TextUtils.isEmpty(et_foodnum3)){
            CommonUtils.showShortMsg(this, "请输入食物数量");
            et_foodnum2.requestFocus();
        }else{
            foodsInfoEdit.setFoodname(et_foodname3);
            //类型转化1
            double b=Double.parseDouble(et_foodmoney3);
            foodsInfoEdit.setMoney(b);
            //类型转化2
            int num = Integer.parseInt(et_foodnum3);
            foodsInfoEdit.setNum(num);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    final int iRow = foodsDao.editFoods(foodsInfoEdit);

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
        CommonUtils.showLonMsg(FoodsEditActivity.this, "修改成功");
    }
}
