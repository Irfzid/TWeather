package com.zidney.tweather.retrofit;

import com.zidney.tweather.model.CurrentModel;
import com.zidney.tweather.model.ForecastModel;
import com.zidney.tweather.model.History;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiEndpoint {

    @GET("current.json?key=c0c6fe2963b24384a31123843212605&q=Jakarta&aqi=yes")
    Call<CurrentModel> getCurrent();

    @GET("forecast.json?key=c0c6fe2963b24384a31123843212605&q=Jakarta&days=3&aqi=no&alerts=no")
    Call<ForecastModel> getForecast();

    @GET Call<History> getHistory(@Url String url);

}
