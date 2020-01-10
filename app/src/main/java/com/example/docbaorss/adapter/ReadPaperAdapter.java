package com.example.docbaorss.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.docbaorss.R;
import com.example.docbaorss.model.ReadPaper;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ReadPaperAdapter extends ArrayAdapter<ReadPaper> {

//    Context context;
//    int resource;
//    List<ReadPaper> objects;

    public ReadPaperAdapter(@NonNull Context context, int resource, @NonNull List<ReadPaper> objects) {
        super(context, resource, objects);
//        this.context = context;
//        this.resource = resource;
//        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_row_listview, null);
        }
        ReadPaper readPaper = getItem(position);

        TextView txtTitle =   convertView.findViewById(R.id.txtTitle);
        txtTitle.setText(readPaper.title);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);

        Log.d("aaa", readPaper.image);

        //Picasso.with(getContext()).load(readPaper.image).into(imageView);

        Glide.with(getContext())
                .load(readPaper.image).into(imageView);

        return convertView;
    }
}
