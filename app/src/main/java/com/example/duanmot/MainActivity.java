package com.example.duanmot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.duanmot.listactivity.ListDatSan;
import com.example.duanmot.listactivity.ListThemSan;
import com.example.duanmot.listactivity.ListThongKe;
import com.example.duanmot.themactivity.ThemDatSanActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout cvDatSan, cvThemSan, cvThongKe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Quản Lý Sân Bóng");
        initView();
        cvThemSan.setOnClickListener(this);
        cvDatSan.setOnClickListener(this);
        cvThongKe.setOnClickListener(this);


    }

    private void initView() {
        cvDatSan =  findViewById(R.id.linear_datsan);
        cvThemSan =  findViewById(R.id.linear_themsan);
        cvThongKe =  findViewById(R.id.linear_thongke);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_themsan:
                startActivity(new Intent(MainActivity.this, ListThemSan.class));
                break;
            case R.id.linear_datsan:
                startActivity(new Intent(MainActivity.this, ListDatSan.class));
                break;
            case R.id.linear_thongke:
                startActivity(new Intent(MainActivity.this, ListThongKe.class));
                break;
            default:
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.exit, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_exit:
                xacnhan();
                break;

            default:
        }
        return super.onOptionsItemSelected(item);
    }
    public void xacnhan(){
        final AlertDialog.Builder aBuilder= new AlertDialog.Builder(MainActivity.this);
        aBuilder.setTitle("Thông Báo");
        aBuilder.setIcon(R.drawable.ic_priority_high_black_24dp);
        aBuilder.setMessage("Bạn Có Muốn Trở Về Đăng Nhập");
        aBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setResult(RESULT_OK, new Intent());
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
        aBuilder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });
        aBuilder.show();
    }

}
