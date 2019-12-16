package com.example.duanmot.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.duanmot.R;
import com.example.duanmot.dao.DaoDatSanBong;
import com.example.duanmot.listactivity.ListDatSan;
import com.example.duanmot.model.ModelDatSanBong;

import java.util.List;

public class AdapterShowALL extends BaseAdapter {
    Activity context;
    List<ModelDatSanBong> modellist;
    DaoDatSanBong daoDatSanBong;
    LayoutInflater inflater;

    public AdapterShowALL(Activity context, List<ModelDatSanBong> m) {
        super();
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.modellist = m;
    }

    @Override
    public int getCount() {
        return modellist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        daoDatSanBong = new DaoDatSanBong(context);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_showall_datsan, null);
            viewHolder = new ViewHolder();

            viewHolder.imageView = convertView.findViewById(R.id.image_item_showall);
            viewHolder.tvTen = convertView.findViewById(R.id.textview_item_showall_ten);
            viewHolder.tvSDT = convertView.findViewById(R.id.textview_item_showall_sdt);
            viewHolder.tvTime = convertView.findViewById(R.id.textview_item_showall_gio);
            viewHolder.tvLoaiSan = convertView.findViewById(R.id.textview_item_showall_loaisan);
            viewHolder.tvNgay = convertView.findViewById(R.id.textview_item_showall_ngay);
            viewHolder.imageDelete = convertView.findViewById(R.id.image_item_showall_delete);
            viewHolder.tvThanhToan = convertView.findViewById(R.id.textview_item_showall_thanhtoan);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ModelDatSanBong modelDatSanBong = modellist.get(position);
        viewHolder.tvTen.setText(modelDatSanBong.getmTen());
        viewHolder.tvSDT.setText(modelDatSanBong.getmSDT());
        viewHolder.tvTime.setText(modelDatSanBong.getmGioSan());
        viewHolder.tvNgay.setText(modelDatSanBong.getmDate());
        viewHolder.tvLoaiSan.setText(modelDatSanBong.getmLoaiSan());
        viewHolder.imageView.setImageResource(R.drawable.team);
        viewHolder.imageDelete.setImageResource(R.drawable.deleteuser);

        if (modelDatSanBong.getmThanhToan() == 0) {
            viewHolder.tvThanhToan.setText("Chưa Thanh Toán");
        } else {
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

    public class ViewHolder {
        private TextView tvTen, tvSDT, tvTime, tvThanhToan, tvNgay, tvLoaiSan;
        private ImageView imageView, imageDelete, imageUpdate;
    }
}
