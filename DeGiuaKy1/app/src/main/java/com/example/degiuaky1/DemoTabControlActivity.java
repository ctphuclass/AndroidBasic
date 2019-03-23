package com.example.degiuaky1;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DemoTabControlActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_tab_control);
        onInit();
        tabLayout.setupWithViewPager(viewPager);
        PagerStateAdapter adapter = new PagerStateAdapter(getSupportFragmentManager());
        adapter.addFragment(new MonHocFragment(),"MonHoc");
        adapter.addFragment(new DienThoaiFragment(),"DienThoai");
        viewPager.setAdapter(adapter);
    }

    private void onInit() {
        tabLayout = findViewById(R.id.demo_mTabLayout);
        viewPager = findViewById(R.id.demo_mViewPager);
    }
}
