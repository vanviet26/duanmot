package com.example.duanmot.listactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duanmot.MainActivity;
import com.example.duanmot.R;
import com.example.duanmot.adapter.AdapterSanBong;
import com.example.duanmot.adapter.AdapterViewSanTrong;
import com.example.duanmot.dao.DaoDatSanBong;
import com.example.duanmot.dao.DaoThemSanBong;
import com.example.duanmot.model.ModelDatSanBong;
import com.example.duanmot.model.ModelThemSanBong;
import com.example.duanmot.themactivity.ThemDatSanActivity;
import com.example.duanmot.updateActivity.UpdateDatSan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ListDatSan extends AppCompatActivity {
    ListView listView;
    Button btnngay, btnCheck;
    EditText edtngay;
    List<ModelDatSanBong> modelDatSanBongs;
    List<ModelDatSanBong> modelDatSanBong;
    List<ModelThemSanBong> listThemSan;
    AdapterViewSanTrong adapterViewSanTrong;
    Spinner spinnerLoaiSan;

    String loaisan = "";
    DaoDatSanBong daoDatSanBong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_dat_san);
        initView();
        daoDatSanBong = new DaoDatSanBong(ListDatSan.this);
        btnngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicks();
            }
        });
        getmLoaiSan();
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modelDatSanBongs = daoDatSanBong.viewDatSan(edtngay.getText().toString(), loaisan);
                adapterViewSanTrong = new AdapterViewSanTrong(ListDatSan.this, R.layout.item_list_datsan, modelDatSanBongs);
                listView.setAdapter(adapterViewSanTrong);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ListDatSan.this, UpdateDatSan.class);
                Bundle b = new Bundle();
                modelDatSanBong = daoDatSanBong.viewAll();
                b.putString("masan",modelDatSanBong.get(position).getmMaSan());
                b.putString("ten",modelDatSanBong.get(position).getmTen());
                b.putString("sdt",modelDatSanBong.get(position).getmSDT());
                b.putString("ngay",modelDatSanBong.get(position).getmDate());
                b.putString("loaisan",modelDatSanBong.get(position).getmLoaiSan());
                b.putString("gio",modelDatSanBong.get(position).getmGioSan());
                b.putInt("gia",modelDatSanBong.get(position).getmGia());
                i.putExtras(b);
                startActivity(i);
            }
        });


    }

    public void datePicks() {
        final Calendar calendar = Calendar.getInstance();
        final int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(ListDatSan.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                edtngay.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }


    public void getmLoaiSan() {
        DaoThemSanBong daoThemSanBong = new DaoThemSanBong(ListDatSan.this);
        listThemSan = daoThemSanBong.getALLSanBong();
        ArrayAdapter<ModelThemSanBong> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, listThemSan);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLoaiSan.setAdapter(dataAdapter);
        spinnerLoaiSan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loaisan = listThemSan.get(spinnerLoaiSan.getSelectedItemPosition()).getmLoaiSan();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_them, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                startActivity(new Intent(ListDatSan.this, MainActivity.class));
                break;
            case R.id.menu_add:
                startActivity(new Intent(ListDatSan.this, ThemDatSanActivity.class));
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    public void initView() {
        edtngay = findViewById(R.id.edt_list_ngay);
        btnCheck = findViewById(R.id.button_checkview_san);
        btnngay = findViewById(R.id.button_list_ngay);
        listView = findViewById(R.id.listview_datsan);

        spinnerLoaiSan = findViewById(R.id.spinner_list_loaiSan);

    }


}
