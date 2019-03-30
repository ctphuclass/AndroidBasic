package com.example.degiuaky1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MonHocAdapter extends ArrayAdapter<model> {
    private Context mContext;
    private List<model> list;
    private int mResource;
    private MySQLite SQLite;
    public MonHocAdapter( Context context, int resource,  List<model> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.list = objects;
        this.mResource = resource;
        SQLite = new MySQLite(context);
        SQLite.OpenDB();
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
            viewHolder.ckbCheck = convertView.findViewById(R.id.item_ckb_check);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        final model item = list.get(position);
        if (item.getId() == 1){
            viewHolder.ckbCheck.setChecked(true);
        }
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

        viewHolder.ckbCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                int mStatus = 0;
                if (isChecked){
                    mStatus = 1;
                }
                int isSuccess = SQLite.updateItem(list.get(position).getId(),mStatus);
                if (isSuccess >= 1){
                    Toast.makeText(mContext,"Update field success",Toast.LENGTH_LONG).show();
                }
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
        CheckBox ckbCheck;
    }
}
