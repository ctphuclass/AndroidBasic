package com.example.degiuaky1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ChiTietActivity extends AppCompatActivity {


    TextView textViewTen;
    TextView textViewMaSv;
    TextView textViewTinChi;
    TextView textViewMH;
    TextView textViewMaMH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);
        onInit();
        onGetIntent();
    }

    private void onInit() {
        textViewMaMH = findViewById(R.id.chitiet_tv_mamh);
        textViewMaSv = findViewById(R.id.chitiet_tv_mssv);
        textViewTinChi = findViewById(R.id.chitiet_tv_tinchi);
        textViewTen = findViewById(R.id.chitiet_tv_ten);
        textViewMH = findViewById(R.id.chitiet_tv_tenmh);
    }


    private void onGetIntent() {
        Intent intent = getIntent();
        model item = (model) intent.getSerializableExtra("CTMH");
        textViewTen.setText(String.valueOf(item.getTen()));
        textViewMaSv.setText(String.valueOf(item.getMssv()));
        textViewTinChi.setText(String.valueOf(item.getTinChi()));
        textViewMH.setText(String.valueOf(item.getTenMonHoc()));
        textViewMaMH.setText(String.valueOf(item.getMaMonHoc()));
    }
}
