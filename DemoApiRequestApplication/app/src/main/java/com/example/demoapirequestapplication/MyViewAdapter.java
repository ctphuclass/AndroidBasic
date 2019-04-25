package com.example.demoapirequestapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.demoapirequestapplication.model.StudentModel;

import java.util.List;

public class MyViewAdapter extends RecyclerView.Adapter<MyViewAdapter.ViewHolder> {
    List<StudentModel> models;
    int mResource;
    Context mContext;
    public MyViewAdapter(Context context,int resource, List<StudentModel> objects){
        this.mContext = context;
        this.mResource = resource;
        this.models = objects;
    }
    @NonNull
    @Override
    public MyViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(mResource,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewAdapter.ViewHolder viewHolder, int i) {
        StudentModel model = models.get(i);
        viewHolder.edtCode.setText(model.getStudentCode());
        viewHolder.edtName.setText(model.getName());
        viewHolder.edtAge.setText(String.valueOf(model.getAge()));
        viewHolder.edtClass.setText(model.getStudentClass());
        viewHolder.edtEmail.setText(model.getEmail());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private EditText edtCode;
        private EditText edtName;
        private  EditText edtAge;
        private EditText edtClass;
        private EditText edtEmail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.edtCode = itemView.findViewById(R.id.student_edt_code);
            this.edtName = itemView.findViewById(R.id.student_edt_name);
            this.edtAge = itemView.findViewById(R.id.student_edt_age);
            this.edtClass = itemView.findViewById(R.id.student_edt_class);
            this.edtEmail = itemView.findViewById(R.id.student_edt_email);
        }
    }
}
