package com.example.duanmot.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.duanmot.dao.DaoThemSanBong;
import com.example.duanmot.model.ModelThemSanBong;

import java.util.List;

public class AdapterViewSanTrong extends BaseAdapter {
    public Activity context;
    public LayoutInflater inflater;
    public List<ModelThemSanBong> modelThemSanBongs;
    DaoThemSanBong daoThemSanBong;
    public AdapterViewSanTrong(Activity context,List<ModelThemSanBong> modelThemSanBongs){

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
