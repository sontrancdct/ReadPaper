package com.example.docbaorss.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.docbaorss.R;
import com.example.docbaorss.adapter.PaperAdapter;
import com.example.docbaorss.adapter.ViewPagerAdapter;
import com.example.docbaorss.fragment.EntertainmentFragment;
import com.example.docbaorss.fragment.NewsFragment;
import com.example.docbaorss.fragment.SportFragment;
import com.example.docbaorss.fragment.TechnologyFragment;
import com.example.docbaorss.model.Paper;
import com.google.android.material.tabs.TabLayout;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPager = findViewById(R.id.viewPager);

        addTabs(viewPager);
        ((TabLayout)findViewById(R.id.tablayout)).setupWithViewPager(viewPager);



    }

    public void addTabs(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFrg(new SportFragment(),"Thể Thao");
        viewPagerAdapter.addFrg(new TechnologyFragment(),"Công Nghệ");
        viewPagerAdapter.addFrg(new NewsFragment(),"Tin Tức Trong Ngày");
        viewPagerAdapter.addFrg(new EntertainmentFragment(),"Giải Trí");
        viewPager.setAdapter(viewPagerAdapter);
    }
}
