package com.example.appnhac.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnhac.Adapter.AllAlbumAdapter;
import com.example.appnhac.Model.Album;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtatcaAlbumActivity extends AppCompatActivity {
    RecyclerView recyclerViewAllalbum;
    Toolbar toolbaralbum;
    AllAlbumAdapter allAlbumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtatca_album);
        init();
        GetData();
    }

    private void GetData() {
        Dataservice dataservice= APIService.getService();
        Call<List<Album>> callback=dataservice.GetAllAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> mangalbum= (ArrayList<Album>) response.body();
                allAlbumAdapter=new AllAlbumAdapter(DanhsachtatcaAlbumActivity.this, mangalbum);
                recyclerViewAllalbum.setLayoutManager(new GridLayoutManager(DanhsachtatcaAlbumActivity.this, 2));
                recyclerViewAllalbum.setAdapter(allAlbumAdapter);

            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    private void init() {
        toolbaralbum=findViewById(R.id.toolbarallalbum);
        recyclerViewAllalbum=findViewById(R.id.recycleviewAllalbum);
        setSupportActionBar(toolbaralbum);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("All albums");

        toolbaralbum.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
