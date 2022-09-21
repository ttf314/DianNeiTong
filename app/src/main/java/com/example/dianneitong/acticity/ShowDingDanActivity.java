package com.example.dianneitong.acticity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.dianneitong.Gongju.OnDelBtnClickListener;
import com.example.dianneitong.Gongju.OnEditBtnClickListener;
import com.example.dianneitong.R;
import com.example.dianneitong.adapter.LvDingDanInfoAdapter;
import com.example.dianneitong.dao.DingDanDao;
import com.example.dianneitong.info.DingDanInfo;

import java.util.List;

public class ShowDingDanActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView btn_return, btn_add;   // 返回图片按钮 ，添加图片按钮
    private DingDanDao dingDanDao;    // 食物数据库操作实例
    private List<DingDanInfo> dingDanInfoList;
    private LvDingDanInfoAdapter lvDingDanInfoAdapter;   // 食物信息数据适配器
    private ListView lv_food;   // 食物列表组件
    private Handler mainHandler;   // 主线程


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ding_dan);

        //线程里面
        initView();
        show();
    }
    private void initView(){

        btn_return = findViewById(R.id.btn_return);
        btn_add = findViewById(R.id.btn_add);
        lv_food = findViewById(R.id.lv_dingdan);

        dingDanDao = new DingDanDao();//实例化
        mainHandler = new Handler(getMainLooper());

        btn_return.setOnClickListener(this);
        btn_add.setOnClickListener(this);
    }
    private void show() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dingDanInfoList = dingDanDao.getAllDingDanList();   // 获取所有的食物数据
                Log.i("管理界面的数据", "食物数量：" + dingDanInfoList.size());
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
        if (lvDingDanInfoAdapter == null) {   // 首次加载时的操作
            lvDingDanInfoAdapter = new LvDingDanInfoAdapter(this, dingDanInfoList);
            lv_food.setAdapter(lvDingDanInfoAdapter);
            //lv_food.setAdapter(lvFoodsInfoAdapter);
        } else {   // 更新数据时的操作
            lvDingDanInfoAdapter.setDingDanInfos(dingDanInfoList);
            lvDingDanInfoAdapter.notifyDataSetChanged();//
        }

// 修改按钮的操作
        lvDingDanInfoAdapter.setOnEditBtnClickListener(new OnEditBtnClickListener() {
            @Override
            public void onEditBtnClick(View view, int position) {
                // 修改按钮的操作
                DingDanInfo item = dingDanInfoList.get(position);//position数据
                Bundle bundle = new Bundle();
                bundle.putSerializable("userEdit", item);
                Intent intent = new Intent(ShowDingDanActivity.this, FoodsEditActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, 1);
            }
        });

        // 删除按钮的操作
        lvDingDanInfoAdapter.setOnDelBtnClickListener(new OnDelBtnClickListener() {
            @Override
            public void onDelBtnClick(View view, int position) {
                //  删除方法
                final DingDanInfo item = dingDanInfoList.get(position);
                new AlertDialog.Builder(ShowDingDanActivity.this)
                        .setTitle("删除确定")
                        .setMessage("您确定要删除：id:[" + item.getDingdanid() + "]，foodname:[" +
                                item.getFoodname() + "]的食物信息吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                delDingdan(item.getDingdanid());
                            }
                        })
                        .setNegativeButton("取消", null)
                        .create().show();
            }
        });
    }
    private void delDingdan(final int id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final int iRow = dingDanDao.delDingdan(id);
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        show();  // 重新加载数据
                    }
                });
            }
        }).start();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_return:
                finish();
                break;
            case R.id.btn_add:
                // 代码桩，打开添加食物界面
                Intent intent = new Intent(this, AddFoodsActivity.class);
                startActivityForResult(intent, 1);
                break;
        }
    }
}
