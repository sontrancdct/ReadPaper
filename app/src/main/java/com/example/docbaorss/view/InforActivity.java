package com.example.docbaorss.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.docbaorss.R;
import com.example.docbaorss.adapter.PaperAdapter;
import com.example.docbaorss.asyntask.ReadRss;
import com.example.docbaorss.model.Paper;

import java.util.ArrayList;

public class InforActivity extends AppCompatActivity {

    ListView listView;
    PaperAdapter paperAdapter;
    ArrayList<Paper> papers;
    ReadRss readRss;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor);

        listView = findViewById(R.id.lvInfor);
        papers = new ArrayList<>();

        readRss = new com.example.docbaorss.asyntask.ReadRss();
        readRss.setAsynListener(listPaper -> {
            this.papers = listPaper;
            paperAdapter = new PaperAdapter(InforActivity.this, R.layout.item_row_a, papers);
            listView.setAdapter(paperAdapter);
            paperAdapter.notifyDataSetChanged();
        });


        readRss.getDataFromUrl("https://cdn.24h.com.vn/upload/rss/congnghethongtin.rss");
        listView.setOnItemClickListener((parent, view1, position, id) -> {
            Intent intent = new Intent(InforActivity.this, DetailsActivity.class);
            intent.putExtra("link", papers.get(position).link);
            startActivity(intent);
        });
    }
}
