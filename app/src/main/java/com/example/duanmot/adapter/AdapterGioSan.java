package com.example.duanmot.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duanmot.R;
import com.example.duanmot.model.ModelThemSanBong;
import com.example.duanmot.model.Modelgio;

import java.util.List;

public class AdapterGioSan extends BaseAdapter {
    LayoutInflater inflater;
    Activity activity;
    List<Modelgio> list;
    public AdapterGioSan(Activity activity, List<Modelgio> list){
        super();
        this.activity =activity;
        this.list= list;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return list.size();
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
       ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_spinnergio, null);
            viewHolder = new ViewHolder();
            viewHolder.tvName = convertView.findViewById(R.id.textview_itemspinner_loaisan);
            viewHolder.imagesan = convertView.findViewById(R.id.image_itemspinner_giosan);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Modelgio h = list.get(position);
        viewHolder.tvName.setText(h.getGio());
        viewHolder.imagesan.setImageResource(R.drawable.wallclock);

        return convertView;
    }

    public class ViewHolder {
        TextView tvName;
        ImageView imagedown,imagesan;
    }
}
