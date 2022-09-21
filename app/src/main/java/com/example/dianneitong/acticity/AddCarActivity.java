package com.example.dianneitong.acticity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.dianneitong.SQLite.CarSQLiteDao;
import com.example.dianneitong.SQLite.GetSQLite;
import com.example.dianneitong.Gongju.CommonUtils;
import com.example.dianneitong.R;
import com.example.dianneitong.SQLite.Tb_car;
import com.example.dianneitong.dao.FoodsDao;
import com.example.dianneitong.info.FoodsInfo;

import java.util.ArrayList;
import java.util.List;

public class AddCarActivity extends AppCompatActivity {
    private TextView tv_carfoodname, tv_carfoodmoney,
            tv_carfoodmoneysum,tv_id3,tv_foodnum;
    private Spinner sp_num;
    private FoodsDao foodsDao;   // 食物数据操作类实例
    private Handler mainHandler;
    private FoodsInfo foodsInfoEdit;   // 当前要修改的用户信息
    private Button bt_addcar;
    int numid,sum;
    double zhong;

    double numid2,sum2;
    double zhong2;


    private double price;
    private String[]num2={"1","2","3","4","5","6","7","8","9","10","11","12","13","14"};
    private List<String> num = new ArrayList<String>();// 数量的集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        bt_addcar=findViewById(R.id.bt_addcar);

        tv_carfoodname=findViewById(R.id.tv_carfoodname);
        tv_carfoodmoney=findViewById(R.id.tv_carfoodmoney);
        tv_carfoodmoneysum=findViewById(R.id.tv_carfoodmoneysum);
        sp_num=findViewById(R.id.sp_num);


        tv_foodnum=findViewById(R.id.tv_foodnum);

        tv_id3=findViewById(R.id.tv_id3);
        Intent intent =getIntent();
        if (intent!=null){
            tv_id3.setText(intent.getStringExtra("userid"));
        }

        GetSQLite getSQLite=new GetSQLite();
        num=getSQLite.setnum();//获取数量集合

        ArrayAdapter<String> numAdapter = new ArrayAdapter<>(
                this, R.layout.spinner_item_year, num);
        sp_num.setAdapter(numAdapter);
        //sp_num.setSelection(numid);//设置默认选项


        foodsDao=new FoodsDao();
        mainHandler = new Handler(getMainLooper());

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            foodsInfoEdit = (FoodsInfo) bundle.getSerializable("userEdit");
            //类型转换
            //viewHolder.tv_money.setText(String.valueOf(item.getMoney()));
            // viewHolder.tv_num.setText(item.getNum()+"");
            tv_carfoodname.setText(foodsInfoEdit.getFoodname());
            tv_carfoodmoney.setText(String.valueOf(foodsInfoEdit.getMoney()));

            //et_foodnum2.setText(foodsInfoEdit.getNum()+"");
            //tv_createDt.setText(userEdit.getCreateDt());
            //zhong=Integer.valueOf(String.valueOf(foodsInfoEdit.getMoney()));
        }
        foodsDao = new FoodsDao();
        mainHandler = new Handler(getMainLooper());

        zhong=foodsInfoEdit.getMoney();
        zhong2=foodsInfoEdit.getMoney();

        sum=(int)zhong;
        sum2=(int)zhong2;

        MyItemSelectedListener itemSelectedListener = new MyItemSelectedListener();
        sp_num.setOnItemSelectedListener(itemSelectedListener);


      /*  bt_addcar.setOnContextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });*/
    }
    private class MyItemSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int position, long id) {
            switch (parent.getId()) {
                case R.id.sp_num:// 如果是选择省份列表
                    numid = position;
                    break;
                default:
                    break;
            }
            int a;
            a=(numid+1)*sum;
            double b;
            b=(numid+1)*sum2;

            int c;
            c=numid+1;

            tv_carfoodmoneysum.setText(Html.fromHtml("<font color=red><b>"
                    + b
            ));

            tv_foodnum.setText(Html.fromHtml("<font color=red><b>"
                    + c
            ));
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    //把食物添加到购物车
    public void bt_addcar(View view) {
        String foodname=tv_carfoodname.getText().toString();

        CarSQLiteDao carSQLiteDao = new CarSQLiteDao(AddCarActivity.this);
        Intent intent =getIntent();
        Integer ID =Integer.valueOf(intent.getStringExtra("userid"));

        Tb_car tb_car = new Tb_car(ID,
                carSQLiteDao.getMaxId()+1,
                tv_carfoodname.getText().toString(),
                Integer.valueOf(tv_foodnum.getText().toString()),
                Double.parseDouble(tv_carfoodmoneysum.getText().toString())
                );

        carSQLiteDao.addcar(tb_car);
        CommonUtils.showLonMsg(AddCarActivity.this, "添加成功！！！");
    }
}
