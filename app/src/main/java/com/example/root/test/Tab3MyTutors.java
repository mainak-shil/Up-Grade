package com.example.root.test;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class Tab3MyTutors extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3mytutors, container, false);
        return rootView;
        }

//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//        lv=(ListView)getView().findViewById(R.id.listview);
//
//
//
//        MenuAdapter menuAdapter = new MenuAdapter(getActivity(),TabActivity.c,TabActivity.names);
//        lv.setAdapter(menuAdapter);

    }


