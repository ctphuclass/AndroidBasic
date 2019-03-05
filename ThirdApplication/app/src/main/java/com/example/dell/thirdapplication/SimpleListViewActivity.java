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

import com.example.dell.thirdapplication.model.loginModel;

import java.util.ArrayList;
import java.util.List;

public class SimpleListViewActivity extends AppCompatActivity {
    ListView listView;
    List<loginModel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_list_view);
        onInit();
        onSetData();
        ArrayAdapter<loginModel> adapter = new ArrayAdapter(SimpleListViewActivity.this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                loginModel model = (loginModel) listView.getItemAtPosition(i);
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + model.getPhoneNumber()));
                if (ContextCompat.checkSelfPermission(SimpleListViewActivity.this,
                        Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(SimpleListViewActivity.this,
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

    private void onSetData() {
        list = new ArrayList();
        list.add(new loginModel("ThanhDat",21,"+84975565700"));
        list.add(new loginModel("Tommy",40,"+849876542"));
        list.add(new loginModel("Kelvin",34,"+849843678"));
        list.add(new loginModel("Kelvin1",34,"+849843678"));
        list.add(new loginModel("Kelvin2",34,"+849843678"));
        list.add(new loginModel("Kelvin3",34,"+849843678"));
        list.add(new loginModel("Kelvin4",34,"+849843678"));
        list.add(new loginModel("Kelvin5",34,"+849843678"));
        list.add(new loginModel("Kelvin6",34,"+849843678"));
    }

    private void onInit() {
        listView = findViewById(R.id.lv_simple_demo);
    }
}
