package com.example.dell.thirdapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dell.thirdapplication.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class DemoSimpleListViewActivity extends AppCompatActivity {
    List<UserModel> modelList;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_simple_list_view);
        onInit();
        onSetValue();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,modelList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                UserModel model = (UserModel) listView.getItemAtPosition(i);
                Toast.makeText(DemoSimpleListViewActivity.this,model.toString(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:+84975565710"));
                if (ContextCompat.checkSelfPermission(DemoSimpleListViewActivity.this,
                        Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(DemoSimpleListViewActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            1);

                    // MY_PERMISSIONS_REQUEST_CALL_PHONE is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                } else {
                    //You already have permission
                    try {
                        startActivity(intent);
                    } catch(SecurityException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    private void onSetValue() {
        modelList = new ArrayList<>();
        modelList.add(new UserModel("Alex",22));
        modelList.add(new UserModel("John",23));
        modelList.add(new UserModel("Kelvin",24));
        modelList.add(new UserModel("Tommy",25));
    }

    private void onInit() {
        listView = findViewById(R.id.lvSimple);
    }

}
