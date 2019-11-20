package com.example.duanmot.updateActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.duanmot.themactivity.ThemSanActivity;

public class UpdateThemSan extends AppCompatActivity {
    EditText edtLoaiSan;
    Button btnCapNhat;
    DaoThemSanBong daoThemSanBong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_them_san);
        edtLoaiSan = findViewById(R.id.edt_update_themsan);
        btnCapNhat = findViewById(R.id.button_update_themsan);
        daoThemSanBong = new DaoThemSanBong(UpdateThemSan.this);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
     String loaisan =b.getString("loaiSan");
     edtLoaiSan.setText(loaisan);
       final String masan = b.getString("maSan");
       btnCapNhat.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (daoThemSanBong.updateSanBong(masan,edtLoaiSan.getText().toString())>0){
                   Toast.makeText(UpdateThemSan.this, "Update Thành Công", Toast.LENGTH_SHORT).show();
               }
           }
       });

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
                startActivity(new Intent(UpdateThemSan.this, MainActivity.class));
                break;
            case R.id.menu_quayve:
                startActivity(new Intent(UpdateThemSan.this, ListThemSan.class));
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}
