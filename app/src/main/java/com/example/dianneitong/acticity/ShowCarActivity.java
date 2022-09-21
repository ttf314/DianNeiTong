package com.example.dianneitong.acticity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.dianneitong.R;
import com.example.dianneitong.SQLite.CarSQLiteDao;
import com.example.dianneitong.SQLite.Tb_car;

import java.util.List;
public class ShowCarActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView btn_return2, btn_add2;   // 返回图片按钮 ，添加图片按钮
    private ListView lv_car;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_car);
        btn_return2 = findViewById(R.id.btn_return2);
        btn_add2 = findViewById(R.id.btn_add2);
        lv_car=findViewById(R.id.lv_car);
        showInfo();
        btn_return2.setOnClickListener(this);
        btn_add2.setOnClickListener(this);
    }

    private void showInfo() {

        String[]carinfos=null;
        ArrayAdapter<String>arrayAdapter=null;
        CarSQLiteDao carSQLiteDao=new CarSQLiteDao(ShowCarActivity.this);
        List<Tb_car>list=carSQLiteDao.getDate(0,(int)carSQLiteDao.getCount());
        carinfos=new  String[list.size()];
        int m=0;
        for(Tb_car tb_car:list){
            carinfos[m]=tb_car.getId()+"|   食物名称:"+tb_car.getFoodname()+"  数量:"+
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
            case R.id.btn_return2:
                finish();
                break;
            case R.id.btn_add2:
                // 代码桩，打开添加食物界面
                Intent intent = new Intent(this, AddFoodsActivity.class);
                startActivityForResult(intent, 1);
                break;
        }
    }
}
