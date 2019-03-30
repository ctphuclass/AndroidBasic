package com.example.degiuaky1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DienThoaiFragment extends Fragment {
    ListView listView;
    List<model> modelList;
    MySQLite sqLite;
    Button btnAddItem;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dien_thoai, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        onInit();
        sqLite = new MySQLite(getContext());
        sqLite.OpenDB();
        modelList = sqLite.getAll();
        if (modelList.size() < 1){
            model item = new model("Mac LeNin","MAC-123","5","Datto-san","1606020012");
            sqLite.addItem(item);
            modelList.addAll(sqLite.getAll());
        }
        MonHocAdapter adapter = new MonHocAdapter(getContext(),R.layout.monhoc_item,modelList);
        listView.setAdapter(adapter);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),CreateItemActivity.class);
                startActivity(intent);
            }
        });
    }

    private void onInit() {
        listView = getActivity().findViewById(R.id.demo_tab_sLvMH);
        btnAddItem = getActivity().findViewById(R.id.demo_btn_Add);
    }
}
