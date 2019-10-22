package com.example.recyclerview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyAdapter3 extends RecyclerView.Adapter<MyAdapter3.ViewHolder> {
    private ArrayList<StudentEntity> mData;
    private final static String TAG = "MyAdapter3";
    private OnItemClickListener mOnItemClickListener;

    public MyAdapter3(ArrayList<StudentEntity> data) {
        this.mData = data;
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener)
    {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void updateData(ArrayList<StudentEntity> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // 绑定数据
        StudentEntity se = mData.get(position);

        holder.mTv1.setText(se.getAge().toString());
        holder.mTv2.setText(se.getName());

        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTv1, mTv2;

        public ViewHolder(View itemView) {
            super(itemView);
            mTv1 = (TextView) itemView.findViewById(R.id.title);
            mTv2 = (TextView) itemView.findViewById(R.id.info);
        }
    }
}
