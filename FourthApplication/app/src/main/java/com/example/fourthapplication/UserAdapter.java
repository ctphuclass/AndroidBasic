package com.example.fourthapplication;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fourthapplication.Database.MySQLite;
import com.example.fourthapplication.model.UserModel;

import java.util.List;

public class UserAdapter extends ArrayAdapter<UserModel> {
    Context mContext;
    int mResource;
    MySQLite sqLite;
    public UserAdapter(Context context, int resource, List<UserModel> objects,MySQLite sqLite) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
        this.sqLite = sqLite;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (viewHolder == null){
            convertView = LayoutInflater.from(mContext).inflate(mResource,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.textViewName = convertView.findViewById(R.id.demo_tv_Name);
            viewHolder.textViewAge = convertView.findViewById(R.id.demo_tv_Age);
            viewHolder.textViewAddress = convertView.findViewById(R.id.demo_tv_address);
            viewHolder.imageView = convertView.findViewById(R.id.demo_iV_call);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        final UserModel model = getItem(position);
        viewHolder.textViewName.setText(model.getUserName());
        viewHolder.textViewAge.setText(String.valueOf(model.getAge()));
        viewHolder.textViewAddress.setText(model.getAddress());

        viewHolder.textViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,model.getUserName() + " hello there!",Toast.LENGTH_LONG).show();
            }
        });
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserDialog dialog = new UserDialog(mContext,R.layout.add_new_user_item,sqLite);
                dialog.show(((FragmentActivity)mContext).getSupportFragmentManager(),"1");
            }
        });

        return convertView;
    }

    public class ViewHolder{
        public TextView textViewName;
        public TextView textViewAge;
        public TextView textViewAddress;
        public ImageView imageView;
    }
}
