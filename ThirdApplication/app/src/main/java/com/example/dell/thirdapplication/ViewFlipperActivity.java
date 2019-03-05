package com.example.dell.thirdapplication;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class ViewFlipperActivity extends AppCompatActivity {
    ViewFlipper viewFlipper;
    Button btnNext;
    int[] imageList = {R.drawable.a,R.drawable.b,R.drawable.c};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper);
        onInit();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        for (int i:imageList){
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(layoutParams);
            imageView.setImageResource(i);
            viewFlipper.addView(imageView);
        }

        viewFlipper.addView(CreateSimpleLinea());
        viewFlipper.addView(SampleView());
        //viewFlipper.setAutoStart(true);
        //viewFlipper.setFlipInterval(500);
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        viewFlipper.setBackgroundColor(getResources().getColor(R.color.colorPink));
        viewFlipper.setInAnimation(in);
        viewFlipper.setOutAnimation(out);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.showNext();
            }
        });
    }
    public View SampleView(){
        LayoutInflater inflater  = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = (View)inflater.inflate(R.layout.sample_view,null);
        return view;
    }

    public LinearLayout CreateSimpleLinea(){
        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        TextView textView = new Button(this);
        textView.setText(getResources().getString(R.string.app_name));
        linearLayout.addView(textView);
        return linearLayout;
    }
    private void onInit() {
        viewFlipper = findViewById(R.id.viewFlipper);
        btnNext = findViewById(R.id.btnNext);
    }
}
