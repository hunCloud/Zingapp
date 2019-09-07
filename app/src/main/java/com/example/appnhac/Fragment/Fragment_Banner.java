package com.example.appnhac.Fragment;


//import android.app.Fragment;
//import androidx.fragment.app.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.appnhac.Adapter.BannerAdapter;
import com.example.appnhac.Model.Quangcao;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Banner extends Fragment {
    Runnable runnable;
    Handler handler;
    View view;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    BannerAdapter bannerAdapter;
    int currentItem;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_banner, container, false);
        mappingToLayout();
        GetData();
        return  view;
    }

    private void mappingToLayout(){
        viewPager=view.findViewById(R.id.viewpager);
        circleIndicator=view.findViewById(R.id.indicatordefault);

    }

    private void GetData() {
        Dataservice dataservice= APIService.getService();
        Call<List<Quangcao>> callback=dataservice.GetDataBanner();
        callback.enqueue(new Callback<List<Quangcao>>() {
            @Override
            public void onResponse(Call<List<Quangcao>> call, Response<List<Quangcao>> response) {
                ArrayList<Quangcao> banners = (ArrayList<Quangcao>) response.body();

                bannerAdapter=new BannerAdapter(getActivity(), banners);
                viewPager.setAdapter(bannerAdapter);
                circleIndicator.setViewPager(viewPager);

                handler=new Handler();
                runnable=new Runnable() {
                    @Override
                    public void run() {
                        currentItem = viewPager.getCurrentItem();
                        currentItem++;
                        if (currentItem >= viewPager.getAdapter().getCount())
                            currentItem = 0;
                        viewPager.setCurrentItem(currentItem, true);
                        handler.postDelayed(runnable, 4000);
                    }


                };
                handler.postDelayed(runnable, 4000);
            }

            @Override
            public void onFailure(Call<List<Quangcao>> call, Throwable t) {

            }
        });

    }
}
