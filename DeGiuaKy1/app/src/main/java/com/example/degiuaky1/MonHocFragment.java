package com.example.degiuaky1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MonHocFragment extends Fragment {
    ListView listView;
    List<model> modelList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ontSetValue();
        View view = inflater.inflate(R.layout.fragment_mon_hoc, container, false);
        listView = view.findViewById(R.id.demo_tab_lvMH);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        MonHocAdapter adapter = new MonHocAdapter(getContext(),R.layout.monhoc_item,modelList);
        listView.setAdapter(adapter);
    }

    private void ontSetValue() {
        modelList = new ArrayList<>();
        modelList.add(new model("Android co ban","2TH129","3.0","Nguyen Thanh Dat","1606020012"));
        modelList.add(new model("Anh van 2","2DC008","3.0","Nguyen Thanh Dat","1606020012"));
        modelList.add(new model("Lap trinh PHP can ban","2TH127","3.0","Nguyen Thanh Dat","1606020012"));
        modelList.add(new model("Thiet ke huong doi tuong","2TH130","3.0","Nguyen Thanh Dat","1606020012"));
    }
}
