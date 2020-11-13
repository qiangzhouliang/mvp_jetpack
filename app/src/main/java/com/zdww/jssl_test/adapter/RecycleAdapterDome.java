package com.zdww.jssl_test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.zdww.jssl_test.R;
import com.zdww.jssl_test.bean.ItemBean;

import java.util.List;

public class RecycleAdapterDome extends RecyclerView.Adapter<RecycleAdapterDome.MyViewHolder>{
    private Context context;
    private List<ItemBean> list;
    private View inflater;
    //构造方法，传入数据
    public RecycleAdapterDome(Context context, List<ItemBean> list){
        this.context = context;
        this.list = list;
    }
    
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建ViewHolder，返回每一项的布局
        inflater = LayoutInflater.from(context).inflate(R.layout.item_dome,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(inflater);
        return myViewHolder;
    }
    
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //将数据和控件绑定
        holder.title.setText(list.get(position).getTitle());
        holder.time.setText(list.get(position).getTime());
        holder.img.setImageResource(list.get(position).getImg());
    }
    
    @Override
    public int getItemCount() {
        //返回Item总条数
        return list.size();
    }
    
    //内部类，绑定控件
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView time;
        ImageView img;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            time = (TextView) itemView.findViewById(R.id.time);
            img = (ImageView) itemView.findViewById(R.id.img);
        }
    }
}