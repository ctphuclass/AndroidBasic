package com.example.fourthapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fourthapplication.model.UserModel;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UserRecylerAdapter extends RecyclerView.Adapter<UserRecylerAdapter.ViewHolder> {
    private Context mContext;
    private int mResource;
    private List<UserModel> modelList;
    public UserRecylerAdapter(Context context, int resource, List<UserModel> objects){
        this.mContext = context;
        this.mResource = resource;
        this.modelList = objects;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(mResource,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final UserModel model = modelList.get(i);
        viewHolder.textViewName.setText(model.getUserName());
        viewHolder.textViewAge.setText(String.valueOf(model.getAge()));
        viewHolder.textViewAddress.setText(model.getAddress());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = getbmpfromURL("https://vidohostingcom.000webhostapp.com/aokhoacbegai.jpg");
                viewHolder.imageView.post(new Runnable() {
                    @Override
                    public void run() {
                        viewHolder.imageView.setImageBitmap(bitmap);
                    }
                });
            }
        };
        Thread thread = new Thread(runnable);
//        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public TextView textViewAge;
        public TextView textViewAddress;
        public ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.demo_tv_Name);
            textViewAddress = itemView.findViewById(R.id.demo_tv_address);
            textViewAge = itemView.findViewById(R.id.demo_tv_Age);
            imageView = itemView.findViewById(R.id.demo_iV_call);
        }
    }

    private Bitmap onLoadImage(String uri){
        try {
            URL url = new URL(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Bitmap getbmpfromURL(String surl){
        try {
            URL url = new URL(surl);
            HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
            urlcon.setDoInput(true);
            urlcon.connect();
            InputStream in = urlcon.getInputStream();
            Bitmap mIcon = BitmapFactory.decodeStream(in);
            return  mIcon;
        } catch (Exception e) {
            String i = e.getMessage();
            Log.e("Error", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
