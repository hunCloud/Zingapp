package com.example.appnhac.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnhac.Adapter.DanhsachbaihatAdapter;
import com.example.appnhac.Model.Album;
import com.example.appnhac.Model.Baihat;
import com.example.appnhac.Model.Playlist;
import com.example.appnhac.Model.Quangcao;
import com.example.appnhac.Model.TheLoai;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.Dataservice;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachbaihatActivity extends AppCompatActivity {
    Quangcao quangcao;
    ImageView imgdanhsachcakhuc;
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewdanhsachbaihat;
    FloatingActionButton floatingActionButton;
    ArrayList<Baihat> mangbaihat;
    DanhsachbaihatAdapter danhsachbaihatAdapter;
    Playlist playlist;
    TheLoai theLoai;
    Album album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachbaihat);
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        DataIntent();

        anhxa();
        init();
        if(quangcao!=null&& !quangcao.getTenBaiHat().equals("")){
            setValueInView(quangcao.getTenBaiHat(), quangcao.getHinhBaiHat());
            GetDataQuangcao(quangcao.getId());
        }
        if(playlist!=null&&!playlist.getTen().equals("")){
            setValueInView(playlist.getTen(), playlist.getHinhPlayList());
            GetDataPlaylist(playlist.getIdPlaylist());
        }
        if(theLoai!=null &&!theLoai.getTenTheLoai().equals("")){
            setValueInView(theLoai.getIdTheLoai(), theLoai.getHinhTheLoai());
            GetDataTheLoai(theLoai.getIdTheLoai());
        }
        if(album!=null&&!album.getTenAlbum().equals("")){
            setValueInView(album.getTenAlbum(), album.getHinhAlbum());
            GetDataAlbum(album.getIdAlbum());
        }
    }

    private void GetDataAlbum(String idAlbum) {
        Dataservice dataservice=APIService.getService();
        Call<List<Baihat>> callback=dataservice.GetDanhsachbaihattheoalbum(idAlbum);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat= (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter=new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });

    }

    private void GetDataPlaylist(String idplaylist) {
        Dataservice dataservice=APIService.getService();
        Call<List<Baihat>> callback=dataservice.GetDanhsachbaitheoplaylist(idplaylist);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat= (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter=new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }
    private void GetDataTheLoai(String idtheloai){
        Dataservice dataservice=APIService.getService();
        Call<List<Baihat>> callback=dataservice.GetDanhsachbaihattheotheloai(idtheloai);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat= (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter=new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void GetDataQuangcao(String idquangcao) {
        Dataservice dataservice= APIService.getService();
        Call<List<Baihat>> callback=dataservice.GetDanhsachbaihattheoquangcao(idquangcao);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat= (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter=new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });

    }

    private void setValueInView(String ten, String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        try {
            URL url=new URL(hinh);
            try {
                Bitmap bitmap= BitmapFactory.decodeStream(url.openConnection().getInputStream() );
                BitmapDrawable bitmapDrawable=new BitmapDrawable(getResources(), bitmap);
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN){
                    collapsingToolbarLayout.setBackground(bitmapDrawable);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(hinh).into(imgdanhsachcakhuc);
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);

        floatingActionButton.setEnabled(false);
    }

    private void anhxa() {
        coordinatorLayout=findViewById(R.id.coordinatorlayout);
        collapsingToolbarLayout=findViewById(R.id.collapsingtoolbar);
        toolbar=findViewById(R.id.toolbardanhsach);
        recyclerViewdanhsachbaihat=findViewById(R.id.recycleviewdanhsachbaihat);
        floatingActionButton=findViewById(R.id.floatingactionbutton);
        imgdanhsachcakhuc=findViewById(R.id.imageviewdanhsachcakhuc);
    }

    private void DataIntent() {
        Intent intent=getIntent();
        if(intent!=null){
            if(intent.hasExtra("banner")){
                quangcao= (Quangcao) intent.getSerializableExtra("banner");
                }
            if(intent.hasExtra("itemplaylist")){
                playlist= (Playlist) intent.getSerializableExtra("itemplaylist");
            }
            if(theLoai!=null &&!theLoai.getTenTheLoai().equals("")){
                setValueInView(theLoai.getTenTheLoai(), theLoai.getHinhTheLoai());
            }
            if(intent.hasExtra("album")){
                album= (Album) intent.getSerializableExtra("idalbum");
            }
        }
    }
    private void eventClick(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DanhsachbaihatActivity.this, PlayNhacActivity.class);
                intent.putExtra("cacbaihat", mangbaihat);
                startActivity(intent);
            }
        });
    }
}
