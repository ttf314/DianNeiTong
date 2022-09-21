package com.example.dianneitong.acticity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dianneitong.Gongju.OnAddBtnClickListener;
import com.example.dianneitong.R;
import com.example.dianneitong.adapter.LvFoodsInfoAdapter2;
import com.example.dianneitong.dao.FoodsDao;
import com.example.dianneitong.info.FoodsInfo;

import java.util.List;

public class YongHuMainActivity extends AppCompatActivity {
    private TextView uid;

    private FoodsDao foodsDao;    // 食物数据库操作实例
    private List<FoodsInfo> foodsInfos;
    //private List<FoodsInfo> foodsinfoList;   // 食物数据集合
    private LvFoodsInfoAdapter2 lvFoodsInfoAdapter;   // 食物信息数据适配器
    private ListView lv_food;   // 食物列表组件
    private Handler mainHandler;   // 主线程

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yong_hu_main);
        //线程里面
        initView();
        show();
    }
    private void initView(){

        uid=findViewById(R.id.Tx_id);
        Intent intent =getIntent();
        if (intent!=null){
            uid.setText(intent.getStringExtra("userid"));
        }


        lv_food = findViewById(R.id.lv_food2);
        foodsDao = new FoodsDao();//实例化
        mainHandler = new Handler(getMainLooper());


    }
    private void show() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                foodsInfos = foodsDao.getAllUserList();   // 获取所有的食物数据
                Log.i("管理界面的数据", "食物数量：" + foodsInfos.size());
                //回到主线程显示数据
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        showLvData();
                    }
                });
            }
        }).start();
    }

    // 显示列表数据的方法
    private void showLvData() {
        if (lvFoodsInfoAdapter == null) {   // 首次加载时的操作
            lvFoodsInfoAdapter = new LvFoodsInfoAdapter2(this, foodsInfos);
            lv_food.setAdapter(lvFoodsInfoAdapter);
            //lv_food.setAdapter(lvFoodsInfoAdapter);
        } else {   // 更新数据时的操作
            lvFoodsInfoAdapter.setFoodsInfos(foodsInfos);
            lvFoodsInfoAdapter.notifyDataSetChanged();//
        }
// 添加按钮的操作
        lvFoodsInfoAdapter.setOnAddBtnClickListener(new OnAddBtnClickListener() {
            @Override
            public void onAddBtnClick(View view, int position) {
                FoodsInfo item = foodsInfos.get(position);//position数据
                Bundle bundle = new Bundle();
                bundle.putSerializable("userEdit", item);
                Intent intent = new Intent(YongHuMainActivity.this, AddCarActivity.class);
                intent.putExtras(bundle);
                intent.putExtra("userid",uid.getText().toString());
                startActivityForResult(intent, 1);

            }
        });
    }

    public void bt_incar(View view) {

        Intent intent = new Intent(YongHuMainActivity.this, ShowCar2Activity.class);
        intent.putExtra("userid",uid.getText().toString());
        startActivity(intent);

    }
}
