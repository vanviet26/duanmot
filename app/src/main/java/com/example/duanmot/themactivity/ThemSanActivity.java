package com.example.duanmot.themactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmot.MainActivity;
import com.example.duanmot.R;
import com.example.duanmot.dao.DaoThemSanBong;
import com.example.duanmot.listactivity.ListThemSan;
import com.example.duanmot.model.ModelThemSanBong;

public class ThemSanActivity extends AppCompatActivity {
    EditText edtMaSan, edtLoaiSan;
    Button btnThemSan, btnHuy;
    DaoThemSanBong daoThemSanBong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_san);
        initView();
        daoThemSanBong = new DaoThemSanBong(this);

        btnThemSan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelThemSanBong modelThemSanBong = new ModelThemSanBong(edtMaSan.getText().toString(), edtLoaiSan.getText().toString());
                try {
                    if (checkAdd() > 0) {
                        if (daoThemSanBong.addSanBong(modelThemSanBong) > 0) {
                            Toast.makeText(ThemSanActivity.this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ThemSanActivity.this, "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception e) {
                    Log.e("Lỗi: ", e.toString());
                }

            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtMaSan.setText("");
                edtLoaiSan.setText("");
            }
        });

    }

    private void initView() {
        edtMaSan = (EditText) findViewById(R.id.edt_maSan);
        edtLoaiSan = (EditText) findViewById(R.id.edt_loaiSan);
        btnThemSan = (Button) findViewById(R.id.btn_addsan);
        btnHuy = (Button) findViewById(R.id.btn_cancelSan);
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
                setResult(RESULT_OK,new Intent());
                finish();
                startActivity(new Intent(ThemSanActivity.this, MainActivity.class));
                break;
            case R.id.menu_quayve:
                setResult(RESULT_OK,new Intent());
                finish();
                startActivity(new Intent(ThemSanActivity.this, ListThemSan.class));
            default:
        }
        return super.onOptionsItemSelected(item);
    }


    public int checkAdd() {
        int check = 1;
        if (edtLoaiSan.getText().toString().length() == 0 || edtMaSan.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(), "Điền đủ thông tin", Toast.LENGTH_SHORT).show();
            return -1;
        }
        return check;
    }

}
