package com.example.duanmot.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.duanmot.R;
import com.example.duanmot.dao.DaoThemSanBong;
import com.example.duanmot.model.ModelThemSanBong;

import java.util.List;

public class AdapterSanBong extends ArrayAdapter<ModelThemSanBong> {
    private Context context;
    private int resource;
    private List<ModelThemSanBong> modellist;
    DaoThemSanBong daoThemSanBong;
    public AdapterSanBong(Context context, int resource,List<ModelThemSanBong> objects) {
        super(context, resource, objects);
        this.context =context;
        this.resource = resource;
        this.modellist = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView,  ViewGroup parent) {
        daoThemSanBong = new DaoThemSanBong(context);
        ViewHolder viewHolder;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_list_themsan,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = convertView.findViewById(R.id.image_item_themsan);
            viewHolder.tvMaLoai = convertView.findViewById(R.id.textview_item_masan);
            viewHolder.tvLoaiSan = convertView.findViewById(R.id.textview_item_loaisan);
            viewHolder.imageDelete  = convertView.findViewById(R.id.image_item_xoa_themsan);
        convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ModelThemSanBong modelThemSanBong = modellist.get(position);
        viewHolder.tvMaLoai.setText(modelThemSanBong.getmMaSanBong());
        viewHolder.tvLoaiSan.setText(modelThemSanBong.getmLoaiSan());
        viewHolder.imageView.setImageResource(R.drawable.pitch);
        viewHolder.imageDelete.setImageResource(R.drawable.delete);
        viewHolder.imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daoThemSanBong.deleteSanBong(modellist.get(position).getmMaSanBong());
                modellist.remove(position);
                Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
    public class ViewHolder{
        private TextView tvMaLoai,tvLoaiSan;
        private ImageView imageView,imageDelete;
    }
}
