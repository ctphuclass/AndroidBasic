package com.example.degiuaky1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MonHocActivity extends AppCompatActivity {
    ListView listView;
    static  List<model> modelList;
    static MonHocAdapter adapter;
    TextView textViewContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_hoc);
        onInit();
        ontSetValue();
         adapter = new MonHocAdapter(MonHocActivity.this,R.layout.monhoc_item,modelList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                model item = (model) listView.getItemAtPosition(position);
                Intent intent = new Intent(MonHocActivity.this,ChiTietActivity.class);
                intent.putExtra("CTMH",item);
                startActivity(intent);
            }
        });
        textViewContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void ontSetValue() {
        modelList = new ArrayList<>();
        modelList.add(new model("Android co ban","2TH129","3.0","Nguyen Thanh Dat","1606020012"));
        modelList.add(new model("Anh van 2","2DC008","3.0","Nguyen Thanh Dat","1606020012"));
        modelList.add(new model("Lap trinh PHP can ban","2TH127","3.0","Nguyen Thanh Dat","1606020012"));
        modelList.add(new model("Thiet ke huong doi tuong","2TH130","3.0","Nguyen Thanh Dat","1606020012"));
    }

    private void onInit() {
        listView = findViewById(R.id.lvMonHoc);
        textViewContent = findViewById(R.id.tv_content);
    }
}
