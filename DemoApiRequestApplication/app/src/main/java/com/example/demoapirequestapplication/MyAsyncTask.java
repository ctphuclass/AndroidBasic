package com.example.demoapirequestapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MyAsyncTask extends AsyncTask<String,Void, Bitmap> {
    IView mView;
    public MyAsyncTask(IView iView){
        this.mView = iView;
    }
    @Override
    protected Bitmap doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("Content-Type","application/json");
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            String result;
            while ((result = reader.readLine()) != null){
                stringBuffer.append(result);
            }
            result = stringBuffer.toString();
            JSONObject parentObject = new JSONObject(result);
            int resultID = parentObject.getInt("result");
            if (resultID > 0){
                JSONObject responseData = parentObject.getJSONObject("response_data");
                String imageUrl = responseData.getString("image_uri");
                Bitmap bitmap = getbmpfromURL(imageUrl);
                return bitmap;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        mView.onRequestSuccess(bitmap);
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
