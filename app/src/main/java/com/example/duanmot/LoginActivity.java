package com.example.duanmot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    public final String name_Shared_Preferenced = "khoanAdmin";

    EditText edtTK,edtMK;
    Button btnDangNhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Đăng Nhập");
        initView();
        final SharedPreferences sharedPreferences = getSharedPreferences(name_Shared_Preferenced, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("taikhoan","vanviet");
        editor.putString("matkhau","viet01");
        editor.apply();
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferencess = getSharedPreferences(name_Shared_Preferenced, Context.MODE_PRIVATE);
                String tk = sharedPreferencess.getString("taikhoan","");
                String mk = sharedPreferencess.getString("matkhau","");

                if(edtTK.getText().length()==0||edtMK.getText().length()==0){
                    Toast.makeText(getApplicationContext(), "Mời Nhập Đủ Thông Tin", Toast.LENGTH_SHORT).show();
                }else if (edtTK.getText().toString().equals(tk)&&edtMK.getText().toString().equals(mk)){
                    Toast.makeText(getApplicationContext(), "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                }else{
                    Toast.makeText(getApplicationContext(), "Đăng Nhập Không Thành Công", Toast.LENGTH_SHORT).show();
                }



            }
        });
    }

    @Override
    protected void onStart() {

        super.onStart();
    }

    private void initView() {
        edtTK = findViewById(R.id.edit_taikhoan);
        edtMK = findViewById(R.id.edit_matkhau);
        btnDangNhap = findViewById(R.id.but_dangnhap);
    }
}
