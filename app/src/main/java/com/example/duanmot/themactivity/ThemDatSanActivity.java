package com.example.duanmot.themactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.duanmot.MainActivity;
import com.example.duanmot.R;
import com.example.duanmot.dao.DaoDatSanBong;
import com.example.duanmot.dao.DaoThemSanBong;
import com.example.duanmot.listactivity.ListDatSan;
import com.example.duanmot.model.ModelDatSanBong;
import com.example.duanmot.model.ModelThemSanBong;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ThemDatSanActivity extends AppCompatActivity {
    EditText edtSDT, edtTen, edtNgayDat, edtMaSan;
    Button btnChon, btnAddLichSan, btnxoaTrang;
    TextView tvSoTien;
    Spinner spinnerLoaiSan, spinnerGioSan;
    List<ModelThemSanBong> listThemSan;
    String loaiSan = "";
    String giosan = "";
    DaoDatSanBong daoDatSanBong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_dat_san);
        initView();
        getLoaiSan();
        daoDatSanBong = new DaoDatSanBong(ThemDatSanActivity.this);
        tvSoTien.setText("0");
        getGioSan();
        btnChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker();
            }
        });

        btnAddLichSan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelDatSanBong datSanBong = new ModelDatSanBong(edtMaSan.getText().toString(),
                        edtSDT.getText().toString(),
                        edtTen.getText().toString(),
                        edtNgayDat.getText().toString(),
                        loaiSan,giosan,
                        Integer.parseInt(tvSoTien.getText().toString()));

                try {
                    if (CheckAddDat() > 0) {
                        if (daoDatSanBong.addDatSanBong(datSanBong) > 0) {
                            Toast.makeText(ThemDatSanActivity.this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ThemDatSanActivity.this, "Thêm Thất Bại \n Kiểm tra trùng Mã Loại Sân", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception e) {
                    Log.d("Lỗi: ", e.toString());
                }
            }
        });
        btnxoaTrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtMaSan.setText("");
                edtNgayDat.setText("");
                edtSDT.setText("");
                edtTen.setText("");
                tvSoTien.setText("0");
            }
        });


    }

    private void initView() {
        edtTen = findViewById(R.id.edt_ten);
        edtSDT = findViewById(R.id.edt_sdt);
        edtNgayDat = findViewById(R.id.edt_ngaydatsan);
        btnChon = findViewById(R.id.button_ngaydatsan);
        tvSoTien = findViewById(R.id.textview_tiensan);
        spinnerLoaiSan = findViewById(R.id.spinner_loaisan);
        btnAddLichSan = findViewById(R.id.button_thelichdatsan);
        btnxoaTrang = findViewById(R.id.button_xoatrang);
        edtMaSan = findViewById(R.id.edt_maloaisan);
        spinnerGioSan = findViewById(R.id.spinner_giodatsan);
    }

    private void datePicker() {
        final Calendar calendar = Calendar.getInstance();
        final int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(ThemDatSanActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                edtNgayDat.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }

    public void getLoaiSan() {
        DaoThemSanBong daoThemSanBong = new DaoThemSanBong(ThemDatSanActivity.this);
        listThemSan = daoThemSanBong.getALLSanBong();
        ArrayAdapter<ModelThemSanBong> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, listThemSan);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLoaiSan.setAdapter(dataAdapter);
        spinnerLoaiSan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loaiSan = listThemSan.get(spinnerLoaiSan.getSelectedItemPosition()).getmLoaiSan();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void getGioSan() {
        final ArrayList<String> arrayTime = new ArrayList<>();
        arrayTime.add("6h-7h");
        arrayTime.add("7h-8h");
        arrayTime.add("8h-9h");
        arrayTime.add("9h-10h");
        arrayTime.add("10h-11h");
        arrayTime.add("14h-15h");
        arrayTime.add("15h-16h");
        arrayTime.add("16h-17h");
        arrayTime.add("17h-18h");
        arrayTime.add("18h-19h");
        arrayTime.add("19h-20h");
        arrayTime.add("20h-21h");
        arrayTime.add("21h-22h");
        arrayTime.add("22h-23h");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,arrayTime);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGioSan.setAdapter(arrayAdapter);
        spinnerGioSan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    giosan = arrayTime.get(position);
                if (giosan.equalsIgnoreCase("6h-7h")
                        ||giosan.equalsIgnoreCase("7h-8h")
                        ||giosan.equalsIgnoreCase("16h-17h")){
                    tvSoTien.setText("300000");
                }else if (giosan.equalsIgnoreCase("14h-15h")
                        ||giosan.equalsIgnoreCase("15h-16h")
                        || giosan.equalsIgnoreCase("8h-9h")
                        ||giosan.equalsIgnoreCase("9h-10h")
                        || giosan.equalsIgnoreCase("10h-11h")){
                    tvSoTien.setText("270000");
                }else{
                    tvSoTien.setText("330000");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public int CheckAddDat() {
        int check = 1;
        if (edtTen.getText().toString().length() == 0 || edtNgayDat.getText().toString().length() == 0 ||
                edtMaSan.getText().toString().length() == 0 || edtSDT.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(), "Nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            return -1;
        }
        return check;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                setResult(RESULT_OK,new Intent(ThemDatSanActivity.this, MainActivity.class));
               onBackPressed();
                break;
            case R.id.menu_quayve:
                setResult(RESULT_OK, new Intent(ThemDatSanActivity.this, ListDatSan.class));
                onBackPressed();
            default:
        }
        return super.onOptionsItemSelected(item);
    }

}





