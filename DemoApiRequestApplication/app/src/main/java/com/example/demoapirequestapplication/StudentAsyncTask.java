package com.example.demoapirequestapplication;

import android.os.AsyncTask;

import com.example.demoapirequestapplication.IHolder.IView;
import com.example.demoapirequestapplication.model.StudentModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

public class StudentAsyncTask extends AsyncTask<String,Void, JSONObject> {
    private IView mView;
    public StudentAsyncTask(IView iView){
        this.mView = iView;
    }
    @Override
    protected JSONObject doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("Content-Type","application/json");
            connection.setRequestMethod("POST");
            // ----- add request params//

            //------------//
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            String result;
            while ((result = bufferedReader.readLine()) != null){
                stringBuffer.append(result);
            }
            result  = stringBuffer.toString();
            JSONObject parentObject = new JSONObject(result);
            return parentObject;
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
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
        try {
            int mResult = jsonObject.getInt("result");
            if (mResult > 0){
                JSONArray jsonArray = jsonObject.getJSONArray("response_data");
                mView.onGetDataSuccess(jsonArray);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
