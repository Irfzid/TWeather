package com.zidney.tweather;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zidney.tweather.model.CurrentModel;
import com.zidney.tweather.model.ForecastModel;
import com.zidney.tweather.model.Forecastday;
import com.zidney.tweather.model.History;
import com.zidney.tweather.retrofit.ApiService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements DayAdapter.OnDayListener {
    private final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recadapter;
    private DayAdapter dayAdapter;
    private List<Forecastday> forecastdays = new ArrayList<>();
    private static MainActivity mainActivity;

    TextView temp_c, loct, condt, windkph, precipmm, humidity, cloud,
            gustkph, lastupdate, pressuremb, condtfore, date, avgtemp_c,
            day, co, o3, no2, so2, pm2_5, pm10, hidate, hitemp, hicondi;
    ImageView icon_w, icon_h, iconm1, iconm2, iconm3, iconm4, iconm5, iconm6;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        temp_c = findViewById(R.id.tv_temp);
        loct = findViewById(R.id.tv_loct);
        condt = findViewById(R.id.tv_condt);
        windkph = findViewById(R.id.tv_windkph);
        precipmm = findViewById(R.id.tv_precipmm);
        humidity = findViewById(R.id.tv_humidity);
        cloud = findViewById(R.id.tv_cloud);
        gustkph = findViewById(R.id.tv_gustkph);
        lastupdate = findViewById(R.id.tv_lastupdate);
        pressuremb = findViewById(R.id.tv_pressuremb);
        icon_w = findViewById(R.id.image_cond);
        day = findViewById(R.id.tv_day);
        iconm1 = findViewById(R.id.iconm1);
        iconm2 = findViewById(R.id.iconm2);
        iconm3 = findViewById(R.id.iconm3);
        iconm4 = findViewById(R.id.iconm4);
        iconm5 = findViewById(R.id.iconm5);
        iconm6 = findViewById(R.id.iconm6);

        co = findViewById(R.id.tv_CO2);
        o3 = findViewById(R.id.tv_O32);
        no2 = findViewById(R.id.tv_NO22);
        so2 = findViewById(R.id.tv_SO22);
        pm2_5 = findViewById(R.id.tv_pm252);
        pm10 = findViewById(R.id.tv_pm102);

        hidate = findViewById(R.id.tv_hidate);
        hitemp = findViewById(R.id.tv_hitemp);
        hicondi = findViewById(R.id.tv_hicondi);
        icon_h = findViewById(R.id.image_h);

        iconm1.setImageResource(R.drawable.wind);
        iconm2.setImageResource(R.drawable.wind);
        iconm3.setImageResource(R.drawable.precipitation);
        iconm4.setImageResource(R.drawable.pressure);
        iconm5.setImageResource(R.drawable.humidity);
        iconm6.setImageResource(R.drawable.cloud);

        recyclerView = findViewById(R.id.fcast);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mainActivity = this;


        getCurrentDatafromAPI();
        getHistoryDatafromAPI();
        getForecastDatafromAPI();

    }

    private void getCurrentDatafromAPI() {
        ApiService.endpoint().getCurrent()
                .enqueue(new Callback<CurrentModel>() {

            @Override
            public void onResponse(Call<CurrentModel> call, Response<CurrentModel> response) {
                if (response.isSuccessful()) {

                    CurrentModel currentModel = response.body();
                    String daycurrent = currentModel.getCurrent().getLast_updated();
                    SimpleDateFormat fromAPI = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    SimpleDateFormat myformat = new SimpleDateFormat("HH:mm, dd MMM yyyy");
                    SimpleDateFormat dayformat = new SimpleDateFormat("EEEE");
                    String reformat = null;
                    String reformat2 = null;
                    try {
                        reformat = dayformat.format(fromAPI.parse(daycurrent));
                        reformat2 = myformat.format(fromAPI.parse(daycurrent));

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    day.setText(reformat);

                    Glide.with(MainActivity.this)
                            .load("http:" + currentModel.getCurrent().getCondition().getIcon())
                            .into(icon_w);
                    temp_c.setText(Double.toString(currentModel.getCurrent().getTemp_c())+"℃");
                    condt.setText(currentModel.getCurrent().getCondition().getText());
                    humidity.setText(Integer.toString(currentModel.getCurrent().getHumidity()) + "%");
                    precipmm.setText(Double.toString(currentModel.getCurrent().getPrecip_mm()) + " mm");
                    cloud.setText(Integer.toString(currentModel.getCurrent().getCloud()) + "%");
                    lastupdate.setText(reformat2);
                    pressuremb.setText(Double.toString(currentModel.getCurrent().getPressure_mb()) + " mb");
                    windkph.setText(Double.toString(currentModel.getCurrent().getWind_kph()) + " kph");
                    gustkph.setText(Double.toString(currentModel.getCurrent().getGust_kph()) + " kph");
                    loct.setText(response.body().getLocation().getName());

                    //Air Quality
                    co.setText(Float.toString(currentModel.getCurrent().getAir_quality().getCo())+"\nμg/m3");
                    o3.setText(Float.toString(currentModel.getCurrent().getAir_quality().getO3())+"\nμg/m3");
                    no2.setText(Float.toString(currentModel.getCurrent().getAir_quality().getNo2())+"\nμg/m3");
                    so2.setText(Float.toString(currentModel.getCurrent().getAir_quality().getSo2())+"\nμg/m3");
                    pm2_5.setText(Float.toString(currentModel.getCurrent().getAir_quality().getPm2_5())+"\nμg/m3");
                    pm10.setText(Float.toString(currentModel.getCurrent().getAir_quality().getPm10())+"\nμg/m3");


                }


            }

            @Override
            public void onFailure(Call<CurrentModel> call, Throwable t) {
                Log.d(TAG,t.toString());

            }
        });
        }

        public void getForecastDatafromAPI(){
        ApiService.endpoint().getForecast()
                .enqueue(new Callback<ForecastModel>() {
                    @Override
                    public void onResponse(Call<ForecastModel> call, Response<ForecastModel> response) {

                        if(response.isSuccessful()){

                            forecastdays = response.body().getForecast().getForecastday();
                            dayAdapter = new DayAdapter(forecastdays, mainActivity);
                            recyclerView.setAdapter(dayAdapter);
                        }
                    }
                    @Override
                    public void onFailure(Call<ForecastModel> call, Throwable t) {
                        Log.d(TAG, t.toString());

                    }
                });
        }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getHistoryDatafromAPI(){
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String history = dateFormat.format(calendar.getTime());
        Log.d(TAG, String.valueOf(dateFormat.format(calendar.getTime())));

        ApiService.endpoint().getHistory("history.json?key=c0c6fe2963b24384a31123843212605&q=Jakarta&dt=" + history)
                .enqueue(new Callback<History>() {
                    @Override
                    public void onResponse(Call<History> call, Response<History> response) {
                        forecastdays = response.body().getForecast().getForecastday();

                        hidate.setText(forecastdays.get(0).getDate());
                        hitemp.setText(Double.toString(forecastdays.get(0).getDay().getAvgtemp_c())+" ℃");
                        hicondi.setText(forecastdays.get(0).getDay().getCondition().getText());
                        Glide.with(MainActivity.this)
                                .load("http:" + forecastdays.get(0).getDay().getCondition().getIcon())
                                .into(icon_h);

                    }

                    @Override
                    public void onFailure(Call<History> call, Throwable t) {

                    }
                });
    }

    @Override
    public void onDayClick(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("Forecastday", forecastdays.get(position));
        startActivity(intent);
        Log.d(TAG, "onDayClick: click");

    }
}


