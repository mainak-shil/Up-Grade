package com.example.root.test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter_courses extends ArrayAdapter

    {

        //1.constructor 2.viewholder inner class 3.ovverride 2 methods getView(..) & getCount(..)

        ArrayList<Courses> items;
//    ArrayList<Courses> items2;

//    public MyAdapter(Context context, int layout, ArrayList<Courses> items2) {
//        super(context, layout);
//        this.items2=items2;
//    }

    public MyAdapter_courses(Context context, int layout, ArrayList<Courses> items) {
        super(context, layout);
        this.items=items;
    }

        //viewholder? contain elements for list item layout... & is an inner class

        public class ViewHolder{
            ImageView iv;
            TextView tv1;
            TextView tv2;
        }

        @Override
        public int getCount() {   //return the no. of list items (by andoid)
        return items.size();
    }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //infalte layout and pass
        //convertview -null or not null
        //null? if list item at 'position' was not initialised earlier
        //not null? if the list item at position' was initialised earlier

        View row;
        row=convertView;
        MyAdapter_courses.ViewHolder viewHolder;
        if(row==null)
        {
            row= LayoutInflater.from(getContext()).inflate(R.layout.listview_look,parent,false);
            viewHolder = new MyAdapter_courses.ViewHolder();
            viewHolder.iv=row.findViewById(R.id.imageView2);
            viewHolder.tv1=row.findViewById(R.id.textView2);
            viewHolder.tv2=row.findViewById(R.id.textView3);
            row.setTag(viewHolder);

        }

        else{
            viewHolder=(MyAdapter_courses.ViewHolder) row.getTag();
        }

        viewHolder.iv.setImageResource(items.get(position).image);
        viewHolder.tv1.setText(items.get(position).courseName);
        viewHolder.tv2.setText(items.get(position).courseSubject);

        return row;

    }
    }

