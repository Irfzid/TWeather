package com.zidney.tweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;

import android.widget.ImageView;

import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zidney.tweather.model.Forecastday;
import com.zidney.tweather.model.Hour;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private RecyclerView hourview;
    private DayDetailAdapter dayDetailAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Hour> hourList = new ArrayList<>();

    TextView tvMaxt, tvMint, tvAvgt, tvdaycond, tvdate, tvwind,
            tvtotprecip, tvhumi, tvuv;
    ImageView imagecon, icon_c, icon_hum, icon_wind, icon_uv, icon_preci;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Forecastday forecastday = getIntent().getParcelableExtra("Forecastday");
        tvMaxt = findViewById(R.id.tv_maxtemp2);
        tvMint = findViewById(R.id.tv_mintemp2);
        tvAvgt = findViewById(R.id.tv_avtemp2);
        tvdate = findViewById(R.id.tv_date);
        tvdaycond = findViewById(R.id.tv_daycond);
        tvwind = findViewById(R.id.tv_maxwind2);
        tvtotprecip = findViewById(R.id.tv_totprec2);
        tvhumi = findViewById(R.id.tv_avghumidity2);
        tvuv = findViewById(R.id.tv_uv2);
        imagecon = findViewById(R.id.image_cond2);
        icon_c = findViewById(R.id.icon_cel);
        icon_hum = findViewById(R.id.icon_hum);
        icon_wind = findViewById(R.id.icon_wind);
        icon_uv = findViewById(R.id.icon_uv);
        icon_preci = findViewById(R.id.icon_prec);

        //change date format
        String datedetail = forecastday.getDate();
        SimpleDateFormat fromAPIde = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat myformatde = new SimpleDateFormat("EEE, dd MMM yyyy");
        String reformatde = null;

        try {
            reformatde = myformatde.format(fromAPIde.parse(datedetail));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tvdate.setText(reformatde);
        tvMaxt.setText(Double.toString(forecastday.getDay().getMaxtemp_c())+"℃");
        tvMint.setText(Double.toString(forecastday.getDay().getMintemp_c())+"℃");
        tvAvgt.setText(Double.toString(forecastday.getDay().getAvgtemp_c())+"℃");
        tvdaycond.setText(forecastday.getDay().getCondition().getText());
        tvwind.setText(Double.toString(forecastday.getDay().getMaxwind_kph())+" kph");
        tvtotprecip.setText(Double.toString(forecastday.getDay().getTotalprecip_mm())+" mm");
        Glide.with(DetailActivity.this)
                .load("http:" + forecastday.getDay().getCondition().getIcon())
                .into(imagecon);
        icon_c.setImageResource(R.drawable.thermometer);
        icon_wind.setImageResource(R.drawable.wind);
        icon_preci.setImageResource(R.drawable.precipitation);
        icon_hum.setImageResource(R.drawable.humidity);
        icon_uv.setImageResource(R.drawable.ultraviolet);

        tvhumi.setText(Integer.toString(forecastday.getDay().getAvghumidity())+"%");
        tvuv.setText(Double.toString(forecastday.getDay().getUv()));

        hourview = findViewById(R.id.fcastdetail);
        layoutManager = new LinearLayoutManager(this);
        hourview.setLayoutManager(layoutManager);

        hourList = forecastday.getHour();
        dayDetailAdapter = new DayDetailAdapter(hourList);
        hourview.setAdapter(dayDetailAdapter);

    }



}