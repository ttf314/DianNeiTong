package com.example.dianneitong.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dianneitong.Gongju.OnDelBtnClickListener;
import com.example.dianneitong.Gongju.OnEditBtnClickListener;
import com.example.dianneitong.Gongju.OnAddBtnClickListener;
import com.example.dianneitong.R;
import com.example.dianneitong.info.FoodsInfo;

import java.util.List;

/**
 * 自定义食物数据适配器类
 */
public class LvFoodsInfoAdapter extends BaseAdapter {
    private Context context;    // 上下文信息，哪个界面调用谁就是
    private List<FoodsInfo> foodsInfos;    // 用户信息数据集合

    private OnEditBtnClickListener onEditBtnClickListener;   // 修改按钮点击事件的监听实例
    private OnDelBtnClickListener onDelBtnClickListener;     // 删除按钮 点击事件的监听实例
    private OnAddBtnClickListener onAddBtnClickListener;     // 添加按钮 点击事件的监听实例


    public LvFoodsInfoAdapter() {
    }

    public LvFoodsInfoAdapter(Context context, List<FoodsInfo> foodsInfos) {
        this.context = context;
        this.foodsInfos = foodsInfos;
        Log.i("数据适配器", "用户数量："+foodsInfos.size());
    }

    public void setFoodsInfos(List<FoodsInfo> foodsInfos) {
        this.foodsInfos = foodsInfos;
    }

    public void setOnEditBtnClickListener(OnEditBtnClickListener onEditBtnClickListener) {
        this.onEditBtnClickListener = onEditBtnClickListener;
    }

    public void setOnDelBtnClickListener(OnDelBtnClickListener onDelBtnClickListener) {
        this.onDelBtnClickListener = onDelBtnClickListener;
    }

    @Override
    public int getCount() {
        return foodsInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return foodsInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView==null){
            //实例化
            convertView = LayoutInflater.from(context).inflate(R.layout.foods_list_item, null);
            //实例化
            viewHolder = new ViewHolder();

            viewHolder.tv_id = convertView.findViewById(R.id.tv_id);
            viewHolder.tv_foodname = convertView.findViewById(R.id.tv_foodname);
            viewHolder.tv_money = convertView.findViewById(R.id.tv_money);
            viewHolder.tv_num = convertView.findViewById(R.id.tv_num);
            //viewHolder.tv_createDt = convertView.findViewById(R.id.tv_createDt);

            viewHolder.btn_edit = convertView.findViewById(R.id.btn_edit);
            viewHolder.btn_delete = convertView.findViewById(R.id.btn_delete);

            //viewHolder.btn_add = convertView.findViewById(R.id.btn_add);


            convertView.setTag(viewHolder);//存viewHolder
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // 这里进行数据填充
        FoodsInfo item = foodsInfos.get(position);
        viewHolder.tv_id.setText(item.getFoodid()+".");
        viewHolder.tv_foodname.setText(item.getFoodname());
        //String money2=""+item.getMoney();
        //viewHolder.tv_money.setText(money2);
        // String strDouble = String.valueOf(toBeString);

        //转化类型
        viewHolder.tv_money.setText(String.valueOf(item.getMoney()));
        viewHolder.tv_num.setText(item.getNum()+"");

        //viewHolder.tv_money.setText("1");
        //viewHolder.tv_num.setText("2");

        // viewHolder.tv_createDt.setText(item.getCreateDt());

        // 修改按钮的点击事件
        viewHolder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditBtnClickListener.onEditBtnClick(v, position);
            }
        });

        // 删除按钮
        viewHolder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDelBtnClickListener.onDelBtnClick(v, position);
            }
        });

        return convertView;
    }

    // 自定义内部类
    private class ViewHolder{
        private TextView tv_id, tv_foodname, tv_money, tv_num;
        private ImageView btn_edit, btn_delete,btn_add;
    }
}