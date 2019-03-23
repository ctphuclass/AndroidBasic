package com.example.degiuaky1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MonHocAdapter extends ArrayAdapter<model> {
    private Context mContext;
    private List<model> list;
    private int mResource;
    public MonHocAdapter( Context context, int resource,  List<model> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.list = objects;
        this.mResource = resource;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (viewHolder == null){
            convertView = LayoutInflater.from(mContext).inflate(mResource,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.textViewMonHoc = convertView.findViewById(R.id.mh_tv_TenMonHoc);
            viewHolder.textViewMaMonHoc = convertView.findViewById(R.id.mh_tv_MaMonHoc);
            viewHolder.textViewTinChi = convertView.findViewById(R.id.mh_tv_TinChi);
            viewHolder.ivDelete = convertView.findViewById(R.id.item_iv_delete);
            viewHolder.ivUpdate = convertView.findViewById(R.id.item_iv_update);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        final model item = list.get(position);
        //  model item1 = getItem(position);
        viewHolder.textViewMaMonHoc.setText(String.valueOf(item.getMaMonHoc()));
        viewHolder.textViewMonHoc.setText(String.valueOf(item.getTenMonHoc()));
        viewHolder.textViewTinChi.setText(String.valueOf(item.getTinChi()));
        viewHolder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(item);
                notifyDataSetChanged();
            }
        });
        viewHolder.ivUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,UpdateChiTietActivity.class);
                intent.putExtra("UPDATEITEM",item);
                intent.putExtra("POS",position);
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }

    public class ViewHolder{
        TextView textViewMonHoc;
        TextView textViewMaMonHoc;
        TextView textViewTinChi;
        ImageView ivUpdate;
        ImageView ivDelete;
    }
}
