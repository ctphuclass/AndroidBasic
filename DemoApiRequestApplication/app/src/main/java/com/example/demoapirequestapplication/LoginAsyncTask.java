package com.example.demoapirequestapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.demoapirequestapplication.IHolder.ILoginView;

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
import java.util.Map;

public class LoginAsyncTask extends AsyncTask<String,Boolean, JSONObject> {
    private Map<String,String> mMap;
    private ILoginView mLoginView;
    private Context mContext;
    private ProgressDialog dialog;
    public LoginAsyncTask(Context context,ILoginView iLoginView,Map<String,String> map){
        this.mMap = map;
        this.mLoginView = iLoginView;
        this.mContext = context;
    }

    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(mContext);
        dialog.setTitle("Loading, please wait .....");
        dialog.show();
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("Content-Type","application/json");
            connection.setRequestMethod("POST");
            // ----- add request params//
            JSONObject jsonObject = new JSONObject();
            Iterator iterator = mMap.keySet().iterator();
            while (iterator.hasNext()){
                String key = (String) iterator.next();
                String value = mMap.get(key);
                jsonObject.put(key,value);
            }

            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            bufferedWriter.write(String.valueOf(jsonObject));
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
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
            publishProgress(true);
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
    protected void onProgressUpdate(Boolean... values) {
        if (values[0] == true){
            dialog.dismiss();
        }
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        if (jsonObject != null){
            try {
                int mResult = jsonObject.getInt("result");
                String mMessage = jsonObject.getString("response_message");
                if (mResult > 0){
                    mLoginView.onLoginSuccess(mMessage);
                }
                else{
                    mLoginView.onLoginFail(mMessage);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
