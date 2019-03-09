package com.example.dell.thirdapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.dell.thirdapplication.model.ContactModel;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {
     ListView listView;
    static List<ContactModel> modelList;
    static ContactAdapter adapter;
    Button btnAddItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        onInit();
        onSetData();

        adapter = new ContactAdapter(ContactActivity.this,R.layout.contact_item,modelList);
        listView.setAdapter(adapter);

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactActivity.this,EditCotactActivity.class);
                startActivity(intent);
            }
        });
    }

    private void onSetData() {
        modelList = new ArrayList();
        modelList.add(new ContactModel(0,"John 0","+8409923029","Tp.HCM"));
        modelList.add(new ContactModel(1,"John 1","+8409923029","Tp.HCM"));
        modelList.add(new ContactModel(2,"John 2","+8409923029","Tp.HCM"));
        modelList.add(new ContactModel(3,"John 3","+8409923029","Tp.HCM"));
        modelList.add(new ContactModel(4,"John 4","+8409923029","Tp.HCM"));
        modelList.add(new ContactModel(5,"John 5","+8409923029","Tp.HCM"));
        modelList.add(new ContactModel(6,"John 6","+8409923029","Tp.HCM"));
        modelList.add(new ContactModel(7,"John 7","+8409923029","Tp.HCM"));
    }

    private void onInit() {
        listView = findViewById(R.id.lv_Contact);
        btnAddItem = findViewById(R.id.contact_btnAdd);
    }

}

