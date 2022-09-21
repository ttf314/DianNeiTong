package com.example.dianneitong.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dianneitong.Gongju.OnAddBtnClickListener;
import com.example.dianneitong.R;
import com.example.dianneitong.info.FoodsInfo;

import java.util.List;

public class LvFoodsInfoAdapter2 extends BaseAdapter {
    private Context context;    // 上下文信息，哪个界面调用谁就是
    private List<FoodsInfo> foodsInfos;    // 用户信息数据集合

    private OnAddBtnClickListener onAddBtnClickListener;     // 添加按钮 点击事件的监听实例

    public LvFoodsInfoAdapter2() {
    }

    public LvFoodsInfoAdapter2(Context context, List<FoodsInfo> foodsInfos) {
        this.context = context;
        this.foodsInfos = foodsInfos;
        Log.i("数据适配器", "用户数量："+foodsInfos.size());
    }

    public void setFoodsInfos(List<FoodsInfo> foodsInfos) {
        this.foodsInfos = foodsInfos;
    }

    public void setOnAddBtnClickListener(OnAddBtnClickListener onAddBtnClickListener) {
        this.onAddBtnClickListener = onAddBtnClickListener;
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
        LvFoodsInfoAdapter2.ViewHolder viewHolder = null;
        if(convertView==null){
            //实例化
            convertView = LayoutInflater.from(context).inflate(R.layout.foods_list_item2, null);
            //实例化
            viewHolder = new LvFoodsInfoAdapter2.ViewHolder();
            viewHolder.tv_id = convertView.findViewById(R.id.tv_id);
            viewHolder.tv_foodname = convertView.findViewById(R.id.tv_foodname);
            viewHolder.tv_money = convertView.findViewById(R.id.tv_money);
            viewHolder.tv_num = convertView.findViewById(R.id.tv_num);
            //viewHolder.tv_createDt = convertView.findViewById(R.id.tv_createDt);

            viewHolder.btn_add = convertView.findViewById(R.id.btn_add);

            //viewHolder.btn_add = convertView.findViewById(R.id.btn_add);


            convertView.setTag(viewHolder);//存viewHolder
        }else{
            viewHolder = (LvFoodsInfoAdapter2.ViewHolder) convertView.getTag();
        }

        // 这里进行数据填充
        FoodsInfo item = foodsInfos.get(position);
        viewHolder.tv_id.setText(item.getFoodid()+".");
        viewHolder.tv_foodname.setText(item.getFoodname());
        //转化类型
        viewHolder.tv_money.setText(String.valueOf(item.getMoney()));
        viewHolder.tv_num.setText(item.getNum()+"");

        // 修改按钮的点击事件
        viewHolder.btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddBtnClickListener.onAddBtnClick(v, position);
            }
        });
        return convertView;
    }

    // 自定义内部类
    private class ViewHolder{
        private TextView tv_id, tv_foodname, tv_money, tv_num;
        private ImageView btn_add;
    }
}