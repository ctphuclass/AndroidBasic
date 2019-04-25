package com.example.demoapirequestapplication;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.demoapirequestapplication.IHolder.IView;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.demo_iV);
        new MyAsyncTask(new IView() {
            @Override
            public void onRequestSuccess(Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onGetDataSuccess(JSONArray jsonArray) {

            }
        }).execute("http://www.vidophp.tk/api/imageservice/getrandomimage");
    }
}
