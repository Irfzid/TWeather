package com.zidney.tweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zidney.tweather.model.Forecastday;
import com.zidney.tweather.model.Hour;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private RecyclerView hourview;
    private RecyclerView.Adapter detailapater;
    private DayDetailAdapter dayDetailAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Hour> hourList = new ArrayList<>();

    TextView tvMaxt, tvMint, tvAvgt, tvdaycond, tvdate, tvwind, tvtotprecip;
    ImageView imagecon;
    
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
        imagecon = findViewById(R.id.image_cond2);

        //change date format
        String datedetail = forecastday.getDate();
        SimpleDateFormat fromAPIde = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat myformatde = new SimpleDateFormat("dd MMM yyyy");
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
        tvwind.setText(Double.toString(forecastday.getDay().getMaxwind_kph())+"kph");
        tvtotprecip.setText(Double.toString(forecastday.getDay().getTotalprecip_mm())+"mm");
        Glide.with(DetailActivity.this)
                .load("http:" + forecastday.getDay().getCondition().getIcon())
                .into(imagecon);

        hourview = findViewById(R.id.fcastdetail);
        layoutManager = new LinearLayoutManager(this);
        hourview.setLayoutManager(layoutManager);

        hourList = forecastday.getHour();
        dayDetailAdapter = new DayDetailAdapter(hourList);
        hourview.setAdapter(dayDetailAdapter);

    }



}