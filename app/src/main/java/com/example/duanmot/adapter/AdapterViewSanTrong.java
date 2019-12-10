package com.example.duanmot.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.example.duanmot.R;
import com.example.duanmot.dao.DaoDatSanBong;
import com.example.duanmot.model.ModelDatSanBong;

import java.util.List;

public class AdapterViewSanTrong extends ArrayAdapter<ModelDatSanBong> {
    private Context context;
    private int resources;
    private List<ModelDatSanBong> modellist;
    DaoDatSanBong daoDatSanBong;
    public AdapterViewSanTrong(Context context, int resource,List<ModelDatSanBong> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resources = resource;
        this.modellist = objects;
    }
    @NonNull
    @Override
    public View getView(final int position, View convertView,  ViewGroup parent) {
        daoDatSanBong = new DaoDatSanBong(context);
        ViewHolder viewHolder;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_list_datsan,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = convertView.findViewById(R.id.image_item_datsan);
            viewHolder.tvTen = convertView.findViewById(R.id.textview_item_datsan_ten);
            viewHolder.tvSDT = convertView.findViewById(R.id.textview_item_datsan_sdt);
            viewHolder.tvTime = convertView.findViewById(R.id.text_item_giosan);
            viewHolder.imageDelete  = convertView.findViewById(R.id.iamge_item_datsan_delte);
            viewHolder.tvThanhToan = convertView.findViewById(R.id.textview_item_datsan_thanhtoan);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ModelDatSanBong modelDatSanBong = modellist.get(position);
        viewHolder.tvTen.setText(modelDatSanBong.getmTen());
        viewHolder.tvSDT.setText(modelDatSanBong.getmSDT());
        viewHolder.tvTime.setText(modelDatSanBong.getmGioSan());
        viewHolder.imageView.setImageResource(R.drawable.team);
        viewHolder.imageDelete.setImageResource(R.drawable.deleteuser);
        if(modelDatSanBong.getmThanhToan() == 0){
            viewHolder.tvThanhToan.setText("Chưa Thanh Toán");
        }else {
            viewHolder.tvThanhToan.setText("Đã Thanh Toán");
        }

        viewHolder.imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daoDatSanBong.deleteDatSan(modellist.get(position).getmMaSan());
                modellist.remove(position);
                Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
    public class ViewHolder{
        private TextView tvTen,tvSDT,tvTime,tvThanhToan;
        private ImageView imageView,imageDelete;
    }
}
