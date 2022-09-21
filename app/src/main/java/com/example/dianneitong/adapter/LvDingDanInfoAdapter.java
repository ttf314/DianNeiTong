package com.example.dianneitong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dianneitong.Gongju.OnDelBtnClickListener;
import com.example.dianneitong.Gongju.OnEditBtnClickListener;
import com.example.dianneitong.R;
import com.example.dianneitong.info.DingDanInfo;

import java.util.List;

public class LvDingDanInfoAdapter extends BaseAdapter {

    private Context context;    // 上下文信息，哪个界面调用谁就是
    private List<DingDanInfo> dingDanInfos;    // 用户信息数据集合

    private OnEditBtnClickListener onEditBtnClickListener;   // 修改按钮点击事件的监听实例
    private OnDelBtnClickListener onDelBtnClickListener;     // 删除按钮 点击事件的监听实例

    public LvDingDanInfoAdapter(Context context, List<DingDanInfo> dingDanInfos) {
        this.context = context;
        this.dingDanInfos = dingDanInfos;
    }

    public void setDingDanInfos(List<DingDanInfo> dingDanInfos) {
        this.dingDanInfos = dingDanInfos;
    }

    public void setOnEditBtnClickListener(OnEditBtnClickListener onEditBtnClickListener) {
        this.onEditBtnClickListener = onEditBtnClickListener;
    }

    public void setOnDelBtnClickListener(OnDelBtnClickListener onDelBtnClickListener) {
        this.onDelBtnClickListener = onDelBtnClickListener;
    }


    @Override
    public int getCount() {
        return dingDanInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return dingDanInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LvDingDanInfoAdapter.ViewHolder viewHolder = null;
        if(convertView==null){
            //实例化
            convertView = LayoutInflater.from(context).inflate(R.layout.dingdan_list_item, null);
            //实例化
            viewHolder = new LvDingDanInfoAdapter.ViewHolder();

            viewHolder.tv_dingdanid2 = convertView.findViewById(R.id.tv_dingdanid);
            viewHolder.tv_userid = convertView.findViewById(R.id.tv_userid);
            viewHolder.tv_foodname = convertView.findViewById(R.id.tv_foodname);
            viewHolder.tv_num = convertView.findViewById(R.id.tv_num);
            viewHolder.tv_money = convertView.findViewById(R.id.tv_money);
            viewHolder.tv_table = convertView.findViewById(R.id.tv_table);
            viewHolder.tv_time = convertView.findViewById(R.id.tv_time);
            //viewHolder.tv_createDt = convertView.findViewById(R.id.tv_createDt);
            viewHolder.btn_delete3 = convertView.findViewById(R.id.btn_delete3);

           // viewHolder.btn_edit3 = convertView.findViewById(R.id.btn_edit3);
           // viewHolder.btn_delete3 = convertView.findViewById(R.id.btn_delete3);
            //viewHolder.btn_add = convertView.findViewById(R.id.btn_add);
            convertView.setTag(viewHolder);//存viewHolder
        }else{
            viewHolder = (LvDingDanInfoAdapter.ViewHolder) convertView.getTag();
        }

        // 这里进行数据填充
        DingDanInfo item = dingDanInfos.get(position);
        viewHolder.tv_userid.setText(item.getId()+"");
        viewHolder.tv_foodname.setText(item.getFoodname());
        viewHolder.tv_num.setText(item.getNum()+"");
        viewHolder.tv_money.setText(String.valueOf(item.getPrice()));
        viewHolder.tv_table.setText(item.getTable());
        viewHolder.tv_time.setText(item.getCreateDt());
        viewHolder.tv_dingdanid2.setText(item.getDingdanid()+".");

        // 修改按钮的点击事件
        // 删除按钮
        viewHolder.btn_delete3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDelBtnClickListener.onDelBtnClick(v,position);
            }
        });

        return convertView;
    }

    private class ViewHolder {
        private TextView tv_dingdanid2, tv_foodname, tv_money, tv_num, tv_userid, tv_time,tv_table;
        private ImageView btn_edit3, btn_delete3, btn_add;
    }
}
/*
    private Context context;    // 上下文信息，哪个界面调用谁就是
    private List<DingDanInfo> dingDanInfos;    // 用户信息数据集合

    private OnEditBtnClickListener onEditBtnClickListener;   // 修改按钮点击事件的监听实例
    private OnDelBtnClickListener onDelBtnClickListener;     // 删除按钮 点击事件的监听实例
    private OnAddBtnClickListener onAddBtnClickListener;     // 添加按钮 点击事件的监听实例


    public LvDingDanInfoAdapter() {

    }
    public LvDingDanInfoAdapter(Context context, List<DingDanInfo> dingDanInfos) {
        this.context = context;
        this.dingDanInfos = dingDanInfos;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setDingDanInfos(List<DingDanInfo> dingDanInfos) {
        this.dingDanInfos = dingDanInfos;
    }

    public void setOnEditBtnClickListener(OnEditBtnClickListener onEditBtnClickListener) {
        this.onEditBtnClickListener = onEditBtnClickListener;
    }

    public void setOnDelBtnClickListener(OnDelBtnClickListener onDelBtnClickListener) {
        this.onDelBtnClickListener = onDelBtnClickListener;
    }

    public void setOnAddBtnClickListener(OnAddBtnClickListener onAddBtnClickListener) {
        this.onAddBtnClickListener = onAddBtnClickListener;
    }

    @Override
    public int getCount() {
        return dingDanInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return dingDanInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LvDingDanInfoAdapter.ViewHolder viewHolder = null;
        if(convertView==null){
            //实例化
            convertView = LayoutInflater.from(context).inflate(R.layout.dingdan_list_item, null);
            //实例化
            viewHolder = new LvDingDanInfoAdapter.ViewHolder();

            viewHolder.tv_dingdanid = convertView.findViewById(R.id.tv_id);
            viewHolder.tv_foodname = convertView.findViewById(R.id.tv_foodname);
            viewHolder.tv_money = convertView.findViewById(R.id.tv_money);
            viewHolder.tv_num = convertView.findViewById(R.id.tv_num);
            viewHolder.tv_userid = convertView.findViewById(R.id.tv_userid);
            viewHolder.tv_time = convertView.findViewById(R.id.tv_time);

            //viewHolder.tv_createDt = convertView.findViewById(R.id.tv_createDt);

            viewHolder.btn_edit3 = convertView.findViewById(R.id.btn_edit3);
            viewHolder.btn_delete3 = convertView.findViewById(R.id.btn_delete3);

            //viewHolder.btn_add = convertView.findViewById(R.id.btn_add);


            convertView.setTag(viewHolder);//存viewHolder
        }else{
            viewHolder = (LvDingDanInfoAdapter.ViewHolder) convertView.getTag();
        }

        // 这里进行数据填充
        DingDanInfo item = dingDanInfos.get(position);

        viewHolder.tv_dingdanid.setText(item.getDingdanid()+".");
        viewHolder.tv_foodname.setText(item.getFoodname());
        //String money2=""+item.getMoney();
        //viewHolder.tv_money.setText(money2);
        // String strDouble = String.valueOf(toBeString);
        //转化类型
        viewHolder.tv_money.setText(String.valueOf(item.getPrice()));
        viewHolder.tv_num.setText(item.getNum()+"");
        viewHolder.tv_userid.setText(item.getId()+"");
        viewHolder.tv_time.setText(item.getCreateDt());
        //viewHolder.tv_money.setText("1");
        //viewHolder.tv_num.setText("2");



       */
/* // 修改按钮的点击事件
        viewHolder.btn_edit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditBtnClickListener.onEditBtnClick(v, position);
            }
        });

        // 删除按钮
        viewHolder.btn_delete3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDelBtnClickListener.onDelBtnClick(v, position);
            }
        });*//*


        return convertView;
    }

    // 自定义内部类
    private class ViewHolder{
        private TextView tv_dingdanid, tv_foodname, tv_money, tv_num,tv_userid,tv_time;
        private ImageView btn_edit3, btn_delete3,btn_add;
    }
*/

/*// 自定义内部类
private class ViewHolder{
    private TextView tv_dingdanid, tv_foodname, tv_money, tv_num,tv_userid,tv_time;
    private ImageView btn_edit3, btn_delete3,btn_add;
}*/
