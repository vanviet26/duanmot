package com.example.duanmot.listactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmot.R;
import com.example.duanmot.dao.DaoDatSanBong;
import com.example.duanmot.updateActivity.UpdateDatSan;

public class ThanhToanList extends AppCompatActivity {
    TextView tvMaSan,tvTen,tvSDT,tvLoaiSan,tvGio,tvNgay,tvGiaTien,tvThanhToan;
    Button btnThanhToan;
    DaoDatSanBong daoDatSanBong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan_list);
        tvMaSan = findViewById(R.id.textview_thanhtoan_masan);
        tvTen = findViewById(R.id.textview_thanhtoan_ten);
        tvSDT = findViewById(R.id.textview_thanhtoan_sdt);
        tvLoaiSan = findViewById(R.id.textview_thanhtoan_loaisan);
        tvGio = findViewById(R.id.textview_thanhtoan_gio);
        tvNgay = findViewById(R.id.textview_thanhtoan_ngay);
        tvGiaTien = findViewById(R.id.textview_thanhtoan_giatien);
        btnThanhToan = findViewById(R.id.checkthanhtoan);
        daoDatSanBong = new DaoDatSanBong(ThanhToanList.this);


        Intent i = getIntent();
        Bundle b = i.getExtras();
         final String maSan = b.getString("masan");
         final String ten = b.getString("ten");
         final String sdt = b.getString("sdt");
         final String loaisan = b.getString("loaisan");
         final String gio = b.getString("gio");
         final String ngay = b.getString("ngay");
         final String giatien = String.valueOf(b.getInt("giatien"));
         final int thanhtoan = b.getInt("thanhtoan");
         tvMaSan.setText(maSan);
         tvTen.setText(ten);
         tvSDT.setText(sdt);
         tvLoaiSan.setText(loaisan);
         tvGio.setText(gio);
         tvNgay.setText(ngay);
         tvGiaTien.setText(giatien);
         btnThanhToan.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (daoDatSanBong.checkThanhToan(maSan,sdt,ten,ngay,loaisan,gio, Integer.parseInt(giatien),1)>0){
                     Toast.makeText(ThanhToanList.this, "Thanh Toán Thành Công", Toast.LENGTH_SHORT).show();
                     setResult(RESULT_OK,new Intent());
                     onBackPressed();
                     startActivity(new Intent(ThanhToanList.this,ListDatSan.class));
                 }
             }
         });
    }

}
