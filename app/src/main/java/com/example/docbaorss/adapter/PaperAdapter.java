package com.example.docbaorss.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.opengl.Visibility;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.docbaorss.R;
import com.example.docbaorss.model.Paper;

import java.util.List;


public class PaperAdapter extends ArrayAdapter<Paper> {
    Activity context;
    int resource;
    List<Paper> objects;


    public PaperAdapter(@NonNull Activity context, int resource, @NonNull List<Paper> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = this.context.getLayoutInflater();
        convertView             = inflater.inflate(this.resource,null);

        TextView txtTitle = convertView.findViewById(R.id.txtTitlePaper);
        TextView txtPubdate = convertView.findViewById(R.id.pubdatePaper);
        TextView txtDetails = convertView.findViewById(R.id.txtDetailsPaper);
        ImageView img = convertView.findViewById(R.id.imageViewPaper);
        ImageView imgLike = convertView.findViewById(R.id.imgLike);
        ImageView imgDisLike = convertView.findViewById(R.id.imgDisLike);

        final Paper paper = this.objects.get(position);

        txtTitle.setText(Html.fromHtml(paper.getTitle()));
        txtDetails.setText(paper.details);
        txtPubdate.setText(paper.pubDate);
        Glide.with(getContext())
                .load(paper.image).into(img);


        imgLike.setOnClickListener(v -> {
            if(imgLike.getVisibility() == View.VISIBLE){
                imgLike.setVisibility(View.GONE);
                imgDisLike.setVisibility(View.VISIBLE);
            }
        });
        imgDisLike.setOnClickListener(v -> {
            if(imgDisLike.getVisibility() == View.VISIBLE){
                imgLike.setVisibility(View.VISIBLE);
                imgDisLike.setVisibility(View.GONE);
            }
        });

        // txtPubdate.setText(Utilily.getPeriod(paper.getPubDate()));

        return convertView;
    }



}
