package com.example.root.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuAdapter extends ArrayAdapter<String>{

    Context c;
    Courses courses[];


    public MenuAdapter(Context context,Courses c[], String names[]) {
        super(context, R.layout.listview_look, R.id.textView2,names);
        this.c=context;
        courses = c;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row =inflater.inflate(R.layout.listview_look,parent,false);

        ImageView image=(ImageView) row.findViewById(R.id.imageView2);
        TextView title = (TextView)row.findViewById(R.id.textView2);
        TextView subtitle = (TextView)row.findViewById(R.id.textView3);

        image.setImageResource(courses[position].getImage());
        title.setText(courses[position].getName());
        subtitle.setText(courses[position].getTeacher());

        return row;
    }
}
