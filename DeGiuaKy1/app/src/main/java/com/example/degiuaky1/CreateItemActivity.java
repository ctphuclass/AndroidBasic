package com.example.degiuaky1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.degiuaky1.MyModel.RegisterSubjectModel;
import com.example.degiuaky1.MyModel.StudentModel;
import com.example.degiuaky1.MyModel.SubjectModel;

import java.util.List;

public class CreateItemActivity extends AppCompatActivity {
    StudentModel studentModel;
    int mStudentId;
    MySQLite sqLite;
    EditText edtStudentName;
    Spinner spinner;
    List<SubjectModel> subjectModelList;
    Button btnCreate;
    Button btnAddUser;
    List<RegisterSubjectModel> registerSubjectModels;
    ListView listViewRegister;
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);
        sqLite = new MySQLite(CreateItemActivity.this);
        sqLite.OpenDB();
        onGetIntent();
        edtStudentName = findViewById(R.id.create_edt_UserName);
        btnCreate = findViewById(R.id.crate_btn_create);
        btnAddUser = findViewById(R.id.create_btn_addUser);
        spinner = findViewById(R.id.create_spn_subject);
        listViewRegister = findViewById(R.id.lvRegisterSubject);

        final ArrayAdapter spinnerAdapter = new ArrayAdapter(CreateItemActivity.this,android.R.layout.simple_dropdown_item_1line,sqLite.getSubjectNames());
        spinner.setAdapter(spinnerAdapter);
        if (studentModel != null){
            edtStudentName.setText(studentModel.getName());
        }
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int SubjectId = sqLite.getSubjectIdByName(spinner.getSelectedItem().toString());
                long isSuccess = sqLite.addRegisterSubject(SubjectId,mStudentId);
                if (isSuccess >= 1){
                    Toast.makeText(CreateItemActivity.this,"Add item success",Toast.LENGTH_LONG).show();
                }
                List<RegisterSubjectModel> sRe = sqLite.getAllRegisterSubject();
                if (arrayAdapter != null){
//                    arrayAdapter.clear();
                    arrayAdapter.add(sRe.get(sRe.size() -1));
                    arrayAdapter.notifyDataSetChanged();
                }
                else{
                    arrayAdapter = new ArrayAdapter(CreateItemActivity.this,android.R.layout.simple_list_item_1,registerSubjectModels);
                    listViewRegister.setAdapter(arrayAdapter);
                }
            }
        });
        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateItemActivity.this,StudentActivity.class);
                startActivity(intent);
            }
        });
    }

    private void onGetIntent() {
        Intent intent = getIntent();
        mStudentId = intent.getIntExtra("Id",0);
        if (mStudentId > 0){
            studentModel = sqLite.getStudentById(mStudentId);
        }
         subjectModelList = sqLite.getAllSubject();
        if (subjectModelList.size() < 1){
            sqLite.addSubjet("Ki Thuat Lap Trinh");
            sqLite.addSubjet("Android Co Ban");
            sqLite.addSubjet("Android Nang Cao");
            subjectModelList.addAll(sqLite.getAllSubject());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerSubjectModels = sqLite.getAllRegisterSubject();
        if (registerSubjectModels.size() > 0){
             arrayAdapter = new ArrayAdapter(CreateItemActivity.this,android.R.layout.simple_list_item_1,registerSubjectModels);
            listViewRegister.setAdapter(arrayAdapter);
        }
    }
}
