package com.example.duanmot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.duanmot.listactivity.ListDatSan;
import com.example.duanmot.listactivity.ListThemSan;
import com.example.duanmot.listactivity.ListThongKe;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    CardView cvDatSan, cvThemSan, cvThongKe;

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
        cvDatSan = (CardView) findViewById(R.id.cardview_datsan);
        cvThemSan = (CardView) findViewById(R.id.cardview_themsan);
        cvThongKe = (CardView) findViewById(R.id.cardview_thongke);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cardview_themsan:
                startActivity(new Intent(MainActivity.this, ListThemSan.class));
                break;
            case R.id.cardview_datsan:
                startActivity(new Intent(MainActivity.this, ListDatSan.class));
                break;
            case R.id.cardview_thongke:
                startActivity(new Intent(MainActivity.this, ListThongKe.class));
                break;
            default:
        }
    }
}
