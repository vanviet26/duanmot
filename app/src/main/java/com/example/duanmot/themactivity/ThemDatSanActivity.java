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
import java.util.Calendar;
import java.util.List;

public class ThemDatSanActivity extends AppCompatActivity {
    EditText edtSDT, edtTen, edtNgayDat, edtgiovao, edtgiora, edtMaSan;
    Button btnChon, btngiovao, btngiora, btnAddLichSan, btnxoaTrang;
    TextView tvSoTien;
    Spinner spinnerLoaiSan;
    List<ModelThemSanBong> listThemSan;
    String loaiSan = "";
    DaoDatSanBong daoDatSanBong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_dat_san);
        initView();
        getLoaiSan();
        daoDatSanBong = new DaoDatSanBong(ThemDatSanActivity.this);
        tvSoTien.setText("0");

        btnChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker();
            }
        });
        btngiovao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeToPicker();
            }
        });
        btngiora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeOutPicker();
            }
        });
        btnAddLichSan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelDatSanBong datSanBong = new ModelDatSanBong(edtMaSan.getText().toString(),
                       edtSDT.getText().toString(),
                        edtTen.getText().toString(),
                        edtNgayDat.getText().toString(),
                        loaiSan, edtgiovao.getText().toString(),
                        edtgiora.getText().toString(),
                        Integer.parseInt(tvSoTien.getText().toString()));

                try {
                        if (CheckAddDat()>0){
                            if (daoDatSanBong.addDatSanBong(datSanBong)>0){
                                Toast.makeText(ThemDatSanActivity.this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(ThemDatSanActivity.this, "Thêm Thất Bại \n Kiểm tra trùng Mã Loại Sân", Toast.LENGTH_SHORT).show();
                            }
                        }
                }catch (Exception e){
                    Log.d("Lỗi: ", e.toString());
                }
            }
        });
        btnxoaTrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtMaSan.setText("");
                edtNgayDat.setText("");
                edtgiora.setText("");
                edtgiovao.setText("");
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
        edtgiovao = findViewById(R.id.edt_giodensan);
        edtgiora = findViewById(R.id.edt_gioroisan);
        btngiora = findViewById(R.id.button_gioroisan);
        btngiovao = findViewById(R.id.button_giodensan);
        btnAddLichSan = findViewById(R.id.button_thelichdatsan);
        btnxoaTrang = findViewById(R.id.button_xoatrang);
        edtMaSan = findViewById(R.id.edt_maloaisan);
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

    private void timeToPicker() {
        final Calendar calendars = Calendar.getInstance();
        final int gio = calendars.get(Calendar.HOUR_OF_DAY);
        final int phut = calendars.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(ThemDatSanActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                SimpleDateFormat simpleDateFormats = new SimpleDateFormat("HH:mm");
                calendars.set(0, 0, 0, hourOfDay, minute);
                edtgiovao.setText(simpleDateFormats.format(calendars.getTime()));

                String vao = edtgiovao.getText().toString();

                if (vao.substring(0, 2).equals("06") || vao.substring(0, 2).equals("05") ||
                        vao.subSequence(0, 2).equals("07") ||
                        vao.substring(0, 2).equals("17")) {
                    tvSoTien.setText("300000");
                } else if (vao.substring(0, 2).equals("01") || vao.substring(0, 2).equals("00")
                        || vao.substring(0, 2).equals("02") || vao.substring(0, 2).equals("03") || vao.substring(0, 2).equals("04")
                ) {
                    Toast.makeText(ThemDatSanActivity.this, "Đang Ngủ Không Rãnh", Toast.LENGTH_SHORT).show();
                } else if (vao.substring(0, 2).equals("18") || vao.substring(0, 2).equals("19") ||
                        vao.substring(0, 2).equals("20") || vao.substring(0, 2).equals("21")) {
                    tvSoTien.setText("320000");
                } else if (vao.substring(0, 2).equals("22") || vao.substring(0, 2).equals("23")) {
                    tvSoTien.setText("350000");
                } else if (vao.length() == 0) {
                    tvSoTien.setText("0");
                } else {
                    tvSoTien.setText("250000");
                }
            }
        }, gio, phut, true);
        timePickerDialog.show();
    }

    private void timeOutPicker() {
        final Calendar calendard = Calendar.getInstance();
        final int gios = calendard.get(Calendar.HOUR_OF_DAY);
        final int phuts = calendard.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(ThemDatSanActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                SimpleDateFormat simpleDateFormatd = new SimpleDateFormat("HH:mm");
                calendard.set(0, 0, 0, hourOfDay, minute);
                edtgiora.setText(simpleDateFormatd.format(calendard.getTime()));

            }
        }, gios, phuts, true);
        timePickerDialog.show();
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

    public int CheckAddDat() {
        int check = 1;
        if (edtTen.getText().toString().length() == 0 ||edtNgayDat.getText().toString().length() == 0 ||
                edtgiovao.getText().toString().length() == 0 || edtgiora.getText().toString().length() == 0 ||
                edtMaSan.getText().toString().length() == 0 || edtSDT.getText().toString().length()==0) {
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
                startActivity(new Intent(ThemDatSanActivity.this, MainActivity.class));
                break;
            case R.id.menu_quayve:
                startActivity(new Intent(ThemDatSanActivity.this, ListDatSan.class));
            default:
        }
        return super.onOptionsItemSelected(item);
    }

}





