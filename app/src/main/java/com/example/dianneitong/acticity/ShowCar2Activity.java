package com.example.dianneitong.acticity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.dianneitong.MySqlHelp;
import com.example.dianneitong.dao.DingDanDao;
import com.example.dianneitong.Gongju.CommonUtils;
import com.example.dianneitong.R;
import com.example.dianneitong.SQLite.CarSQLiteDao;
import com.example.dianneitong.SQLite.GetSQLite;
import com.example.dianneitong.SQLite.Tb_car;
import com.example.dianneitong.dao.UserDao;
import com.example.dianneitong.info.DingDanInfo;
import com.example.dianneitong.info.FoodsInfo;
import com.example.dianneitong.info.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class ShowCar2Activity extends AppCompatActivity implements View.OnClickListener {
    double a,money2;
    int rmbnuy;
    private UserInfo userInfo;   // 当前要修改的用户信息
    private MySqlHelp mySqlHelp;
    private Spinner sp_wei;
    private ListView lv_car;
    private TextView uid,foodnamecar,foodnum,foodMONEY,wei,rmb;
    private int id,cid2;
    private int id2,foodid,RMB;
    private String RMB2;
    private ImageView btn_return3, btn_add;   // 返回图片按钮 ，添加图片按钮
    String CARID;
    int weiid2;
    private DingDanDao dingDanDao;   // 订单数据操作类实例
    private UserDao userDao3;
    private Handler mainHandler;
    private List<String> num = new ArrayList<String>();// 数量的集合

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            //super.handleMessage(msg);
            if(msg.what==0){
                int count = (Integer)msg.obj;
                rmb.setText(""+count);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_car2);
        foodnamecar=findViewById(R.id.foodnamecar);
        foodnum=findViewById(R.id.foodnum);
        foodMONEY=findViewById(R.id.foodMONEY);
        wei=findViewById(R.id.wei);
        sp_wei=findViewById(R.id.sp_wei);
        lv_car=findViewById(R.id.lv_car);
        uid=findViewById(R.id.te_iduser);
        btn_return3 = findViewById(R.id.btn_return3);
        rmb=findViewById(R.id.tv_rmb);

        btn_return3.setOnClickListener(this);
        Intent intent =getIntent();
        if (intent!=null){
            uid.setText(intent.getStringExtra("userid"));
        }
        id=Integer.valueOf(uid.getText().toString());

        //userDao3.getRmbByUid("13");
        //userDao2.getRmbByUid("13");
        //rmb.setText(userDao2.getRmbByUid("13"));

        GetSQLite getSQLite=new GetSQLite();
        num=getSQLite.setnum2();//获取数量集合
        //cars = carSQLiteDaos.find(1);
        ArrayAdapter<String> numAdapter = new ArrayAdapter<>(
                this, R.layout.spinner_item_year, num);
        sp_wei.setAdapter(numAdapter);


        try {
            id2 = Integer.valueOf(uid.getText().toString()).intValue();

        } catch (NumberFormatException e) {

            e.printStackTrace();

        }
        showInfo();

        lv_car.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String info = String.valueOf(((TextView)view).getText());
                String cid = info.substring(0,info.indexOf("|"));
                CommonUtils.showLonMsg(ShowCar2Activity.this, cid);
                //cid2=Integer.getInteger(cid);
                foodMONEY.setText(cid);

                String []carid={cid};
                CARID=carid[0];
                String[]carinfos2=null;
                try {
                    foodid = Integer.valueOf(foodMONEY.getText().toString()).intValue();

                } catch (NumberFormatException e) {

                    e.printStackTrace();
                }

                CarSQLiteDao carSQLiteDao2=new CarSQLiteDao(ShowCar2Activity.this);
                List<Tb_car> list=carSQLiteDao2.getDate3(foodid,0,(int)carSQLiteDao2.getCount());
                carinfos2=new  String[list.size()];
                int m=0;
                for(Tb_car tb_car:list){
                    foodMONEY.setText(String.valueOf(tb_car.getSummoney()));
                    foodnum.setText(String.valueOf(tb_car.getNum()));
                    foodnamecar.setText(String.valueOf(tb_car.getFoodname()));
            carinfos2[m]=tb_car.getCarid()+"|   食物名称:"+tb_car.getFoodname()+"  数量:"+
                    String.valueOf(tb_car.getNum())+"   总价格:"+
                    String.valueOf(tb_car.getSummoney());
                    m++;
                }
            }
        });
        try {
            foodid = Integer.valueOf(foodMONEY.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        dingDanDao=new DingDanDao();

        mainHandler = new Handler(getMainLooper());

        MyItemSelectedListener itemSelectedListener = new MyItemSelectedListener();
        sp_wei.setOnItemSelectedListener(itemSelectedListener);
        doQueryCount();
    }

    private class MyItemSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int position, long id) {
            switch (parent.getId()) {
                case R.id.sp_wei:// 如果是选择省份列表
                    weiid2 = position;
                    break;
                default:
                    break;
            }
            wei.setText(Html.fromHtml("<font color=red><b>"
                    + weiid2
            ));
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private void showInfo() {
        String[]carinfos=null;
        ArrayAdapter<String> arrayAdapter=null;
        CarSQLiteDao carSQLiteDao=new CarSQLiteDao(ShowCar2Activity.this);
        List<Tb_car> list=carSQLiteDao.getDate2(id2,0,(int)carSQLiteDao.getCount());
        carinfos=new  String[list.size()];

        int m=0;
        for(Tb_car tb_car:list){
            carinfos[m]=tb_car.getCarid()+"|   食物名称:"+tb_car.getFoodname()+"  数量:"+
                    String.valueOf(tb_car.getNum())+"   总价格:"+
                    String.valueOf(tb_car.getSummoney());
            m++;
        }
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,carinfos);
        lv_car.setAdapter(arrayAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_return3:
                finish();
                break;
        }
    }

    //买
    public void bt_buy(View view) {
        final String foodname = foodnamecar.getText().toString().trim();
        final String money = foodMONEY.getText().toString().trim();
        money2 = Double.valueOf(money);
        final String num = foodnum.getText().toString().trim();
        Integer  num2 = Integer .valueOf(num);
        Integer  uid2 = Integer .valueOf(uid.getText().toString());
        rmbnuy = Integer.valueOf(rmb.getText().toString());


        final DingDanInfo item = new DingDanInfo();
        item.setId(uid2);
        item.setFoodname(foodname);
        item.setNum(num2);
        item.setPrice(money2);
        item.setTable(wei.getText().toString());
        item.setCreateDt(CommonUtils.getDateStrFromNow());

        if(rmbnuy>money2){
            a=rmbnuy-money2;
            if(a<0){
                a=0;
            }
            CommonUtils.showLonMsg(ShowCar2Activity.this, "添加成功");
        }else {
           a=Double.parseDouble(rmb.getText().toString());
        }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final int iRow = dingDanDao.addDingDan(item);
                    MySqlHelp mySqlHelp=new MySqlHelp();
                    mySqlHelp.editUser2(a+"",uid.getText().toString()+"");
                    //final int iRow2 = userDao3.editUser2(111+"",12+"");
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            setResult(1);   // 使用参数表示当前界面操作成功，并返回管理界面
                            finish();
                        }
                    });
                }
            }).start();

            if(rmbnuy>money2){
            }else {
                CommonUtils.showLonMsg(ShowCar2Activity.this, "卡下余额不足");
            }

    }

    // 执行查询用户数量的方法
    private void doQueryCount(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                int count = MySqlHelp.getUserRmb(uid.getText().toString());
                Message msg = Message.obtain();
                msg.what = 0;   // 查询结果
                msg.obj = count;
                // 向主线程发送数据
                handler.sendMessage(msg);
            }
        }).start();
    }

}
