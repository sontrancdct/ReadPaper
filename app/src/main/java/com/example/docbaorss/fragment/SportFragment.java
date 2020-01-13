package com.example.docbaorss.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.docbaorss.R;
import com.example.docbaorss.adapter.PaperAdapter;
import com.example.docbaorss.asyntask.ReadRss;
import com.example.docbaorss.model.Paper;
import com.example.docbaorss.view.DetailsActivity;
import com.example.docbaorss.view.MainActivity;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class SportFragment extends Fragment {
    ListView listView;
    PaperAdapter paperAdapter;
    ArrayList<Paper> papers;
    ReadRss readRss;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sport, container, false);


        listView = view.findViewById(R.id.lvSport);
        papers = new ArrayList<>();

        readRss = new ReadRss();
        readRss.setAsynListener(listPaper -> {
            this.papers = listPaper;
            paperAdapter = new PaperAdapter(getActivity(), R.layout.item_row_a, papers);
            listView.setAdapter(paperAdapter);
            paperAdapter.notifyDataSetChanged();
        });
        readRss.getDataFromUrl("https://cdn.24h.com.vn/upload/rss/bongda.rss");

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            Intent intent = new Intent(getContext(), DetailsActivity.class);
            intent.putExtra("link", papers.get(position).link);
            startActivity(intent);
        });
        return view;
    }
}
