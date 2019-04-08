package com.example.fourthapplication;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.fourthapplication.Database.MySQLite;
import com.example.fourthapplication.model.UserModel;

@SuppressLint("ValidFragment")
public class UserDialog extends DialogFragment {
    Context mContext;
    int mResource;
    MySQLite mySQLite;
    @SuppressLint("ValidFragment")
    public UserDialog(Context context, int resource, MySQLite sqLite){
        this.mContext = context;
        this.mResource = resource;
        this.mySQLite = sqLite;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final View v = LayoutInflater.from(mContext).inflate(mResource,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add New User")
                .setView(v)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UserModel model = onValidateForm(v);
                        mySQLite.addUser(model);
                        MainActivity.adapter.add(model);
                        MainActivity.adapter.notifyDataSetChanged();
                        getDialog().dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getDialog().dismiss();
                    }
                });
        return builder.create();
    }

    public UserModel onValidateForm(View view){
        UserModel model = new UserModel();
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.textViewUserName = view.findViewById(R.id.dialog_UserName);
        viewHolder.textViewAge = view.findViewById(R.id.dialog_Age);
        viewHolder.textViewAddress = view.findViewById(R.id.dialog_Address);
        if (viewHolder.textViewUserName.getText().toString().length() > 0){
            model.setUserName(viewHolder.textViewUserName.getText().toString());
        }
        else {
            return null;
        }
        if (viewHolder.textViewAge.getText().toString().length() > 0){
            model.setAge(Integer.valueOf(viewHolder.textViewAge.getText().toString()));
        }
        else {
            return null;
        }
        if (viewHolder.textViewAddress.getText().toString().length() > 0){
            model.setAddress(viewHolder.textViewAddress.getText().toString());
        }
        else {
            return null;
        }
        return model;
    }

    public class ViewHolder{
        public TextView textViewUserName;
        public  TextView textViewAge;
        public TextView textViewAddress;
    }
}
