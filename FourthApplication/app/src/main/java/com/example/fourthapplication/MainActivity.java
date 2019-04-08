package com.example.fourthapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.fourthapplication.Database.MySQLite;
import com.example.fourthapplication.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    public static UserAdapter adapter;
    public static List<UserModel> modelList;
    MySQLite sqLite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqLite = new MySQLite(MainActivity.this);
        sqLite.OpenDB();
        onInit();
        onSetValue();
        getSupportFragmentManager();
        adapter = new UserAdapter(MainActivity.this,R.layout.demo_item,modelList,sqLite);
        listView.setAdapter(adapter);
    }

    private void onSetValue() {
        int count = sqLite.countUser();
        if (count < 1){
            long i = sqLite.addUser(new UserModel("Thanh Dat",21,"TP.HCM"));
        }
        modelList = sqLite.getAllUser();
//        modelList = new ArrayList<>();
//        modelList.add(new UserModel("Thanh Dat",21,"TP.HCM"));
//        modelList.add(new UserModel("Tommy",25,"Can Tho"));
//        modelList.add(new UserModel("Kelvin",27,"Ha Noi"));
//        modelList.add(new UserModel("Lee",31,"Hai Phong"));
    }

    private void onInit() {
        listView = findViewById(R.id.demo_lv);
    }
}
