package com.example.dell.thirdapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

public class DemoFlipperActivity extends AppCompatActivity {
    ViewFlipper viewFlipper;
    Button BtnNext;
    Button BtnPrevious;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_flipper);
        onInit();
       // viewFlipper.startFlipping();
        //viewFlipper.setFlipInterval(1000);
        BtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.showNext();;
            }
        });
        BtnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.showPrevious();
            }
        });
    }

    private void onInit() {
        viewFlipper = findViewById(R.id.demo_viewFlipper);
        BtnNext = findViewById(R.id.demo_btnNext);
        BtnPrevious = findViewById(R.id.demo_btnPrevious);
    }
}
