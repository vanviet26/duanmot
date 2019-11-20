package com.example.duanmot.listactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.duanmot.MainActivity;
import com.example.duanmot.R;
import com.example.duanmot.themactivity.ThemDatSanActivity;

public class ListDatSan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_dat_san);
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

}
