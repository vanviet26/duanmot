package com.example.duanmot.listactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.duanmot.MainActivity;
import com.example.duanmot.R;
import com.example.duanmot.dao.DaoDatSanBong;

public class ListThongKe extends AppCompatActivity {
DaoDatSanBong daoDatSanBong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_thong_ke);
        setTitle("Main Thống Kê");
        TextView tvngay = findViewById(R.id.textview_ngaythongke);
        TextView tvthang = findViewById(R.id.textview_thangthongke);
        TextView tvnam = findViewById(R.id.textview_namthongke);
        TextView tv  = findViewById(R.id.textview_chuathanhtoan);
        daoDatSanBong = new DaoDatSanBong(getApplicationContext());
        tvngay.setText("Thống Kê Ngày    :       "   +daoDatSanBong.getDoanhThuNgay());
        tvthang.setText("Thống Kê Tháng  :       "   +daoDatSanBong.getDoanhThuThang());
        tvnam.setText("Thống Kê Năm      :       "   +daoDatSanBong.getDoanhThuNam());
        tv.setText("Chưa Thanh Toán Ngày :       "+daoDatSanBong.chuaThanhToan());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.thong, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_quayve:
                startActivity(new Intent(ListThongKe.this, MainActivity.class));
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}
