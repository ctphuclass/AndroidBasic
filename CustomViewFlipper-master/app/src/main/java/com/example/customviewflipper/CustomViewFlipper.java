package com.example.customviewflipper;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class CustomViewFlipper extends Activity implements View.OnTouchListener {

    ViewFlipper viewFlipper;
    float downX = 0;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewflipper_layout);
        onInit();

        Intent intent = getIntent();

        ImageAdapter adapter = new ImageAdapter(this);
        for( int i = 0; i < ImageAdapter.imageLinks.length; i++){
            new MyAsyncTask(new IView() {
                @Override
                public void OnGetBitmapSuccess(Bitmap bitmap) {
                    ImageView imageView = new ImageView(CustomViewFlipper.this);
                    imageView.setLayoutParams(new AbsListView.LayoutParams(200, 200));
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    imageView.setPadding(10, 10, 10, 10);
                    imageView.setImageBitmap(bitmap);
                    viewFlipper.addView(imageView);
                }
            }).execute(ImageAdapter.imageLinks[i]);
        }

        viewFlipper.setDisplayedChild(intent.getExtras().getInt("Position",0));
        viewFlipper.setOnTouchListener(this);
    }



    private void onInit(){
        viewFlipper = findViewById(R.id.viewFlipper);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                float upX = event.getX();
                if(upX < downX){
                    viewFlipper.showNext();
                }else{
                    viewFlipper.showPrevious();
                }
                break;
        }
        return true;
    }
}
