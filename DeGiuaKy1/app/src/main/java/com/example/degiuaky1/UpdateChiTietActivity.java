package com.example.degiuaky1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateChiTietActivity extends AppCompatActivity {
    EditText edtTMH;
    EditText edtMMH;
    EditText edtTC;
    EditText edtTen;
    EditText edtMSSv;
    Button btnUpdate;
    Button btnBack;
    int position = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_chi_tiet);
        onInit();
        onGetValue();
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model item = onValidateForm();
                if (item != null){
                    MonHocActivity.modelList.remove(position);
                    MonHocActivity.modelList.add(position,item);
                    MonHocActivity.adapter.notifyDataSetChanged();
                    finish();
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private model onValidateForm(){
        String errorText = "This field cannot be blank";
        String TMH = "",MMH = "",TC ="",Ten="",MSSV="";
        TMH = edtTMH.getText().toString();
        if (TMH.length() < 1){
            edtTMH.setError(errorText);
            return null;
        }
        MMH = edtMMH.getText().toString();
        if (MMH.length() < 1){
            edtMMH.setError(errorText);
            return null;
        }
        TC = edtTC.getText().toString();
        if (edtTC.length() < 1){
            edtTC.setError(errorText);
            return null;
        }
        Ten = edtTen.getText().toString();
        if (Ten.length() < 1){
            edtTen.setError(errorText);
            return null;
        }
        MSSV = edtMSSv.getText().toString();
        if (MSSV.length() < 1){
            edtMSSv.setError(errorText);
            return null;
        }

        model item = new model(TMH,MMH,TC,Ten,MSSV);
        return item;
    }

    private void onGetValue() {
        Intent intent = getIntent();
        position = intent.getIntExtra("POS",0);
        model item = (model) intent.getSerializableExtra("UPDATEITEM");
        edtMMH.setText(item.getMaMonHoc());
        edtTMH.setText(item.getTenMonHoc());
        edtTC.setText(item.getTinChi());
        edtTen.setText(item.getTen());
        edtMSSv.setText(item.getTen());
    }

    private void onInit() {
        edtMMH = findViewById(R.id.update_edt_mmh);
        edtTMH = findViewById(R.id.update_edt_tmh);
        edtTC = findViewById(R.id.update_edt_tc);
        edtTen = findViewById(R.id.update_edt_ten);
        edtMSSv = findViewById(R.id.update_edt_mssv);
        btnUpdate = findViewById(R.id.update_btn_update);
        btnBack = findViewById(R.id.update_btn_back);
    }
}
