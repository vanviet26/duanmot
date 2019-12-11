package com.example.duanmot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.duanmot.listactivity.ListDatSan;
import com.example.duanmot.listactivity.ListThemSan;
import com.example.duanmot.listactivity.ListThongKe;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout cvDatSan, cvThemSan, cvThongKe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}
