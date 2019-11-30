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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;

import com.example.duanmot.MainActivity;
import com.example.duanmot.R;
import com.example.duanmot.adapter.AdapterSanBong;
import com.example.duanmot.dao.DaoThemSanBong;
import com.example.duanmot.model.ModelThemSanBong;
import com.example.duanmot.themactivity.ThemDatSanActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ListDatSan extends AppCompatActivity {
    ListView listView;
    Button btnngay,btngiovao,btnCheck, button_gioroisan_dat;
    EditText edtngay,edtgiovao ,edt_gioroisan_dat;
    List<ModelThemSanBong> modelThemSanBongs;
    AdapterSanBong adapterSanBong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_dat_san);
    initView();
        final DaoThemSanBong daoThemSanBong = new DaoThemSanBong(ListDatSan.this);
        btnngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePick();

            }
        });
        btngiovao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeToPickers(edtgiovao);

            }
        });
        button_gioroisan_dat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeToPickers(edt_gioroisan_dat);
            }
        });
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  modelThemSanBongs = daoThemSanBong.getSanByTime(
                          edtgiovao.getText().toString()+"",
                          edt_gioroisan_dat.getText().toString()+"",
                          edtngay.getText().toString()+"");

//                modelThemSanBongs = daoThemSanBong.viewTrong(edtngay.getText().toString(),edtgiovao.getText().toString());
                adapterSanBong = new AdapterSanBong(ListDatSan.this,R.layout.item_list_themsan,modelThemSanBongs);
                listView.setAdapter(adapterSanBong);

            }
        });

    }
    public void DatePick(){
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
    private void timeToPickers(final EditText times) {
        final Calendar calendars = Calendar.getInstance();
        final int gio = calendars.get(Calendar.HOUR_OF_DAY);
        final int phut = calendars.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(ListDatSan.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                SimpleDateFormat simpleDateFormats = new SimpleDateFormat("HH:mm");
                calendars.set(0, 0, 0, hourOfDay, minute);
                times.setText(simpleDateFormats.format(calendars.getTime()));
            }
        }, gio, phut, true);
        timePickerDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_them,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_home:
                startActivity(new Intent(ListDatSan.this, MainActivity.class));
                break;
            case R.id.menu_add:
                startActivity(new Intent(ListDatSan.this, ThemDatSanActivity.class));
            default:
        }
        return super.onOptionsItemSelected(item);
    }
    public void initView(){
        edtgiovao = findViewById(R.id.edt_list_giovao);
        edtngay = findViewById(R.id.edt_list_ngay);
        btnCheck = findViewById(R.id.button_checkview_san);
        btngiovao = findViewById(R.id.button_list_giovao);
        btnngay = findViewById(R.id.button_list_ngay);
        listView = findViewById(R.id.listview_datsan);
        edt_gioroisan_dat = findViewById(R.id.edt_gioroisan_dat);
        button_gioroisan_dat = findViewById(R.id.button_gioroisan_dat);
    }

}
