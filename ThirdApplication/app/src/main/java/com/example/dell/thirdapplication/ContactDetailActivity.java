package com.example.dell.thirdapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.dell.thirdapplication.model.ContactModel;

public class ContactDetailActivity extends AppCompatActivity {
    TextView tvUserName;
    TextView tvPhone;
    TextView tvAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        onInit();
        onGetIntent();
    }

    private void onGetIntent() {
        Intent intent = getIntent();
        ContactModel model = (ContactModel)intent.getSerializableExtra("ContactModel");
        tvUserName.setText("Name: " + model.getName());
        tvPhone.setText("Phone: " + model.getPhoneNumber());
        tvAddress.setText("Address: " + model.getAddress());
    }

    private void onInit() {
        tvUserName = findViewById(R.id.contact_detail_tvUserName);
        tvAddress = findViewById(R.id.contact_detail_tvAddress);
        tvPhone = findViewById(R.id.contact_detail_tvPhone);
    }
}
