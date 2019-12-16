package com.example.duanmot.listactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.duanmot.MainActivity;
import com.example.duanmot.R;
import com.example.duanmot.adapter.AdapterSanBong;
import com.example.duanmot.dao.DaoThemSanBong;
import com.example.duanmot.model.ModelThemSanBong;
import com.example.duanmot.themactivity.ThemSanActivity;
import com.example.duanmot.updateActivity.UpdateThemSan;
import java.util.List;


public class ListThemSan extends AppCompatActivity {
    DaoThemSanBong daoThemSanBong;
    public List<ModelThemSanBong> model;
    ListView listView;
    AdapterSanBong adapterSanBong;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_them_san_bong);
        setTitle("Main Thêm Sân");
        initView();
        daoThemSanBong = new DaoThemSanBong(ListThemSan.this);
        model = daoThemSanBong.getALLSanBong();
        adapterSanBong = new AdapterSanBong(ListThemSan.this,R.layout.item_list_themsan,model);
        listView.setAdapter(adapterSanBong);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent i = new Intent(ListThemSan.this, UpdateThemSan.class);
            Bundle b = new Bundle();
            b.putString("loaiSan",model.get(position).getmLoaiSan());
            b.putString("maSan",model.get(position).getmMaSanBong());
            i.putExtras(b);
            startActivity(i);
            }
        });
    }

    private void initView() {
    listView = findViewById(R.id.listview_themsan);
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
                startActivity(new Intent(ListThemSan.this, MainActivity.class));
                break;
            case R.id.menu_add:
                startActivity(new Intent(ListThemSan.this, ThemSanActivity.class));
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

}

