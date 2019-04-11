package com.example.fourthapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.fourthapplication.Database.MySQLite;
import com.example.fourthapplication.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    RecyclerView recyclerView;
    public static UserAdapter adapter;
    public static UserRecylerAdapter recylerAdapter;
    public static List<UserModel> modelList;
    public static DemoRecyclerViewAdapter demoRecyclerViewAdapter;
    MySQLite sqLite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqLite = new MySQLite(MainActivity.this);
        sqLite.OpenDB();
        onInit();
        onSetValue();

        demoRecyclerViewAdapter = new DemoRecyclerViewAdapter(this,R.layout.demo_item,modelList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(demoRecyclerViewAdapter);
    }

    private void onSetValue() {
        int count = sqLite.countUser();
        if (count < 1){
            long i = sqLite.addUser(new UserModel("Thanh Dat",21,"TP.HCM"));
        }
        modelList = sqLite.getAllUser();
    }

    private void onInit() {
        recyclerView = findViewById(R.id.demo_recylerView);
    }
}
