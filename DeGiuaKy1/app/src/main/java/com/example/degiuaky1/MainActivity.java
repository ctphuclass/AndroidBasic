package com.example.degiuaky1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtUserName;
    EditText edtPassword;
    Button btnLogin;
    String mUserName ="";
    String mPassword = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onInit();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm()){
                    if (mUserName.equals("1606020012") && mPassword.equals("admin")){
                        Intent intent = new Intent(MainActivity.this,MonHocActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Username or Password was not correct!",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private boolean validateForm(){
        mUserName = edtUserName.getText().toString();
        if (mUserName.length() < 1){
            edtUserName.setError("User name cannot be blank");
            return  false;
        }
        mPassword = edtPassword.getText().toString();
        if (mPassword.length() < 1){
            edtPassword.setError("Password cannot be blank");
            return  false;
        }
        return true;
    }

    private void onInit() {
        edtUserName = findViewById(R.id.login_edt_username);
        edtPassword = findViewById(R.id.login_edt_password);
        btnLogin = findViewById(R.id.login_btnLogin);
    }
}
