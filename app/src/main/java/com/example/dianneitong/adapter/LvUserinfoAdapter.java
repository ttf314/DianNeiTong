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
import com.example.dianneitong.R;
import com.example.dianneitong.info.UserInfo;

import java.util.List;

/**
 * 自定义用户数据适配器类
 */
public class LvUserinfoAdapter extends BaseAdapter {
    private Context context;    // 上下文信息，哪个界面调用谁就是
    private List<UserInfo> userinfoList;    // 用户信息数据集合

    private OnEditBtnClickListener onEditBtnClickListener;   // 修改按钮点击事件的监听实例
    private OnDelBtnClickListener onDelBtnClickListener;     // 删除按钮 点击事件的监听实例

    public LvUserinfoAdapter() {
    }

    public LvUserinfoAdapter(Context context, List<UserInfo> userinfoList) {
        this.context = context;
        this.userinfoList = userinfoList;
        Log.i("数据适配器", "用户数量："+userinfoList.size());
    }

    public void setUserinfoList(List<UserInfo> userinfoList) {
        this.userinfoList = userinfoList;
    }

    public void setOnEditBtnClickListener(OnEditBtnClickListener onEditBtnClickListener) {
        this.onEditBtnClickListener = onEditBtnClickListener;
    }

    public void setOnDelBtnClickListener(OnDelBtnClickListener onDelBtnClickListener) {
        this.onDelBtnClickListener = onDelBtnClickListener;
    }

    @Override
    public int getCount() {
        return userinfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return userinfoList.get(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.user_list_item, null);

           //实例化
            viewHolder = new ViewHolder();

            //前面是自定义类的变量，后面是布局的控件
            viewHolder.tv_id = convertView.findViewById(R.id.tv_id2);
            viewHolder.tv_uname = convertView.findViewById(R.id.tv_uname2);
            viewHolder.tv_upass = convertView.findViewById(R.id.tv_upass2);
            viewHolder.tv_rmb = convertView.findViewById(R.id.tv_rmb2);

            //viewHolder.tv_createDt = convertView.findViewById(R.id.tv_createDt);

            viewHolder.btn_edit = convertView.findViewById(R.id.btn_edit);
            viewHolder.btn_delete = convertView.findViewById(R.id.btn_delete);

            convertView.setTag(viewHolder);//存viewHolder
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // 这里进行数据填充
        UserInfo item = userinfoList.get(position);
        viewHolder.tv_id.setText(item.getId()+".");
        viewHolder.tv_uname.setText(item.getUname());
        viewHolder.tv_upass.setText(item.getUpass());
        viewHolder.tv_rmb.setText(item.getRmb()+"");
        //viewHolder.tv_rmb.setText(item.getRmb()+"");
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
        private TextView tv_id, tv_uname, tv_upass, tv_rmb;
        private ImageView btn_edit, btn_delete;
    }

}
