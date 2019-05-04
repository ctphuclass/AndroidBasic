package com.example.simpleforumchatapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MessageAdapter extends ArrayAdapter<MessageModel> {
    private List<MessageModel> modelList;
    private Context mContext;
    private int theirResource;
    private int myResource;
    public MessageAdapter( Context context, int resource1,int resource2, List<MessageModel> objects) {
        super(context, resource1, objects);
        this.modelList = objects;
        this.mContext = context;
        this.theirResource = resource1;
        this.myResource = resource2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        MessageModel model = modelList.get(position);
        if (model.isCurrentUser()){
            convertView = LayoutInflater.from(mContext).inflate(myResource,parent,false);
            viewHolder.tvMessage = convertView.findViewById(R.id.message_body);
            viewHolder.tvMessage.setText(model.getText());
        }
        else{
            convertView = LayoutInflater.from(mContext).inflate(theirResource,parent,false);
            viewHolder.tvMessage = convertView.findViewById(R.id.message_body);
            viewHolder.tvUserName = convertView.findViewById(R.id.name);
            viewHolder.tvUserName.setText(model.getUserName());
            viewHolder.tvMessage.setText(model.getText());
        }
        return convertView;
    }

    public void AddItem(MessageModel model){
        modelList.add(model);
        notifyDataSetChanged();
    }

    private class ViewHolder{
        private TextView tvUserName;
        private TextView tvMessage;
    }
}
