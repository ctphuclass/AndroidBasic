package com.example.fourthapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fourthapplication.model.UserModel;

import java.util.List;

public class DemoRecyclerViewAdapter extends RecyclerView.Adapter<DemoRecyclerViewAdapter.ViewHolder> {
    private Context mContext;
    private int mResource;
    private List<UserModel> modelList;
    public DemoRecyclerViewAdapter(Context context, int resource, List<UserModel> objects){
        this.mContext = context;
        this.mResource = resource;
        this.modelList = objects;
    }

    @NonNull
    @Override
    public DemoRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(mResource,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final UserModel item = modelList.get(i);
        viewHolder.textViewUserName.setText(item.getUserName());
        viewHolder.textViewAge.setText(String.valueOf(item.getAge()));
        viewHolder.textViewAddress.setText(item.getAddress());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewUserName;
        private TextView textViewAge;
        private TextView textViewAddress;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewUserName = itemView.findViewById(R.id.demo_tv_Name);
            this.textViewAddress = itemView.findViewById(R.id.demo_tv_address);
            this.textViewAge = itemView.findViewById(R.id.demo_tv_Age);
        }
    }
}
