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
        TextView tvngay = findViewById(R.id.textview_ngaythongke);
        TextView tvthang = findViewById(R.id.textview_thangthongke);
        TextView tvnam = findViewById(R.id.textview_namthongke);
        daoDatSanBong = new DaoDatSanBong(getApplicationContext());
        tvngay.setText("Thống Kê Ngày        :         "   +daoDatSanBong.getDoanhThuNgay());
        tvthang.setText("Thống Kê Tháng      :         "   +daoDatSanBong.getDoanhThuThang());
        tvnam.setText("Thống Kê Năm          :         "   +daoDatSanBong.getDoanhThuNam());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_home:
                startActivity(new Intent(ListThongKe.this, MainActivity.class));
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}
