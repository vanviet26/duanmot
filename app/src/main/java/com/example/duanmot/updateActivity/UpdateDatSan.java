package com.example.duanmot.updateActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmot.MainActivity;
import com.example.duanmot.R;
import com.example.duanmot.adapter.AdapterGioSan;
import com.example.duanmot.dao.DaoDatSanBong;
import com.example.duanmot.dao.DaoThemSanBong;
import com.example.duanmot.listactivity.ListDatSan;
import com.example.duanmot.listactivity.ListThemSan;
import com.example.duanmot.model.ModelDatSanBong;
import com.example.duanmot.model.ModelThemSanBong;
import com.example.duanmot.model.Modelgio;
import com.example.duanmot.themactivity.ThemDatSanActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UpdateDatSan extends AppCompatActivity {
    Spinner spinnerGio;
    TextView tvGia;
    Button btnCapNhat;
    EditText edtSDT, edtTen;
    DaoDatSanBong daoDatSanBong;
    String giosan = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_dat_san);
        setTitle("Update Đặt Sân");
        initview();
        daoDatSanBong = new DaoDatSanBong(this);
        getGioSan();
        Intent i = getIntent();
        Bundle c = i.getExtras();
        final String masan = c.getString("masan");
        edtTen.setText(c.getString("ten"));
        edtSDT.setText(c.getString("sdt"));
        final String ngay = c.getString("ngay");
        final String loaisan = c.getString("loaisan");
        final int gia = c.getInt("gia");
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (daoDatSanBong.updateDatSan(masan, edtSDT.getText().toString(), edtTen.getText().toString(), ngay, loaisan, giosan, gia) > 0) {
                    Toast.makeText(UpdateDatSan.this, "Update Thành Công", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK, new Intent());
                    onBackPressed();
                    startActivity(new Intent(UpdateDatSan.this, ListDatSan.class));
                }
            }
        });
    }

    private void initview() {
        spinnerGio = findViewById(R.id.spinner_updatedatsan_giodatsan);
        tvGia = findViewById(R.id.textview_Updatedatsan_tiensan);
        btnCapNhat = findViewById(R.id.button_updatedatsan_thelichdatsan);
        edtSDT = findViewById(R.id.edt_updatedatsan_sdt);
        edtTen = findViewById(R.id.edt_updatedatsan_ten);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void getGioSan() {
        final List<Modelgio> arrayTime = new ArrayList<>();
        arrayTime.add(new Modelgio("6h-7h"));
        arrayTime.add(new Modelgio("7h-8h"));
        arrayTime.add(new Modelgio("8h-9h"));
        arrayTime.add(new Modelgio("9h-10h"));
        arrayTime.add(new Modelgio("10h-11h"));
        arrayTime.add(new Modelgio("14h-15h"));
        arrayTime.add(new Modelgio("15h-16h"));
        arrayTime.add(new Modelgio("16h-17h"));
        arrayTime.add(new Modelgio("17h-18h"));
        arrayTime.add(new Modelgio("18h-19h"));
        arrayTime.add(new Modelgio("19h-20h"));
        arrayTime.add(new Modelgio("20h-21h"));
        arrayTime.add(new Modelgio("21h-22h"));
        arrayTime.add(new Modelgio("22h-23h"));
        AdapterGioSan arrayAdapter = new AdapterGioSan(UpdateDatSan.this, arrayTime);

        spinnerGio.setAdapter(arrayAdapter);
        spinnerGio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                giosan = String.valueOf(arrayTime.get(position));
                if (giosan.equalsIgnoreCase("6h-7h")
                        || giosan.equalsIgnoreCase("7h-8h")
                        || giosan.equalsIgnoreCase("16h-17h")) {
                    tvGia.setText("300000");
                } else if (giosan.equalsIgnoreCase("14h-15h")
                        || giosan.equalsIgnoreCase("15h-16h")
                        || giosan.equalsIgnoreCase("8h-9h")
                        || giosan.equalsIgnoreCase("9h-10h")
                        || giosan.equalsIgnoreCase("10h-11h")) {
                    tvGia.setText("270000");
                } else {
                    tvGia.setText("330000");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                setResult(RESULT_FIRST_USER, new Intent());
                finish();
                startActivity(new Intent(UpdateDatSan.this, MainActivity.class));
                break;
            case R.id.menu_quayve:
                setResult(RESULT_FIRST_USER, new Intent());
                onBackPressed();
                startActivity(new Intent(UpdateDatSan.this, ListDatSan.class));
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}

