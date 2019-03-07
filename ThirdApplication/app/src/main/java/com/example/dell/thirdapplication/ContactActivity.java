package com.example.dell.thirdapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.dell.thirdapplication.model.ContactModel;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {
    ListView listView;
    List<ContactModel> modelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        onInit();
        onSetData();

        ContactAdapter adapter = new ContactAdapter(ContactActivity.this,R.layout.contact_item,modelList);
        listView.setAdapter(adapter);
    }

    private void onSetData() {
        modelList = new ArrayList();
        modelList.add(new ContactModel("John 0","+8409923029","Tp.HCM"));
        modelList.add(new ContactModel("John 1","+8409923029","Tp.HCM"));
        modelList.add(new ContactModel("John 2","+8409923029","Tp.HCM"));
        modelList.add(new ContactModel("John 3","+8409923029","Tp.HCM"));
        modelList.add(new ContactModel("John 4","+8409923029","Tp.HCM"));
        modelList.add(new ContactModel("John 5","+8409923029","Tp.HCM"));
        modelList.add(new ContactModel("John 6","+8409923029","Tp.HCM"));
        modelList.add(new ContactModel("John 7","+8409923029","Tp.HCM"));
    }

    private void onInit() {
        listView = findViewById(R.id.lv_Contact);
    }

}

