package com.whether.API;


import com.whether.data.UI.model.data.CityGeoData;
import com.whether.data.UI.model.data.WeatherInfoResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("data/2.5/weather")
    Call<WeatherInfoResponse> callApiForWeatherInfo(@Query("q") String cityId);

    @GET("geo/1.0/direct")
    Observable<List<CityGeoData>> callGeoCoderApi(@Query("q") String cityId);

    @GET("data/2.5/weather")
    Observable<WeatherInfoResponse> callApiForWeatherInfoWithCoordinates(@Query("lat") double lat,@Query("lon") double lon);
}
