package com.example.degiuaky1;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.degiuaky1.MyModel.StudentModel;

import java.util.List;

public class StudentActivity extends AppCompatActivity {
    ListView listView;
    List<StudentModel> modelList;
    MySQLite sqLite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        sqLite = new MySQLite(StudentActivity.this);
        sqLite.OpenDB();
        onGetValue();
        listView = findViewById(R.id.student_lv);
        StudentAdapter adapter = new StudentAdapter(StudentActivity.this,R.layout.student_item,modelList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StudentModel model = (StudentModel)listView.getItemAtPosition(position);
                Intent intent = new Intent(StudentActivity.this,CreateItemActivity.class);
                intent.putExtra("Id",model.getId());
                startActivity(intent);
                finish();
            }
        });
    }

    private void onGetValue() {
        modelList = sqLite.getAllStudent();
        if (modelList.size() < 1){
            long i = sqLite.addStudent("Thanh Dat");
            long k = sqLite.addStudent("Alex");
            sqLite.addStudent("John");
            sqLite.addStudent("Marry");
            sqLite.addStudent("Tommy");
            List<StudentModel> studentModels = sqLite.getAllStudent();
            modelList.addAll(studentModels);
        }
    }

    public class StudentAdapter extends ArrayAdapter<StudentModel>{
        Context mContext;
        int mResourcel;
        public StudentAdapter( Context context, int resource, List<StudentModel> objects) {
            super(context, resource, objects);
            this.mContext = context;
            this.mResourcel = resource;
        }

        @Override
        public View getView(int position, View convertView,ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (viewHolder == null){
                convertView = LayoutInflater.from(mContext).inflate(mResourcel,parent,false);
                viewHolder = new ViewHolder();
                viewHolder.tvName = convertView.findViewById(R.id.studen_item_name);
            }
            else{
                viewHolder = (ViewHolder)convertView.getTag();
            }
            StudentModel item = getItem(position);
            viewHolder.tvName.setText(item.getName());
            return convertView;
        }

        public class ViewHolder{
            private TextView tvName;
        }
    }
}
