package com.whether.data.UI.model;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.whether.API.ApiInterface;
import com.whether.API.RetrofitClient;
import com.whether.data.RequestCompleteListener;
import com.whether.data.UI.model.data.City;
import com.whether.data.UI.model.data.CityGeoData;
import com.whether.data.UI.model.data.WeatherInfoResponse;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherInfoShowModelImpl implements WeatherInfoShowModel{
    private Context mContext;
    public WeatherInfoShowModelImpl(Context applicationContext) {
        mContext = applicationContext;
    }

    @Override
    public void getCityList(RequestCompleteListener<List<City>> callback) {
        String json = null;
        try {
            InputStream is = mContext.getAssets().open("city_list.json"); // your file name
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            Gson gson = new Gson();
            Type cityListType = new TypeToken<List<City>>(){}.getType();

            List<City> cityList = gson.fromJson(json, cityListType);
            callback.onRequestSuccess(cityList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void getWeatherInfo(String city, RequestCompleteListener<WeatherInfoResponse> callback) {
        ApiInterface apiInterface  = RetrofitClient.getClient().create(ApiInterface.class);
        Call<WeatherInfoResponse> call= apiInterface.callApiForWeatherInfo(city);
        call.enqueue(new Callback<WeatherInfoResponse>() {
            @Override
            public void onResponse(Call<WeatherInfoResponse> call, Response<WeatherInfoResponse> response) {
                if (response.body() != null){
                    callback.onRequestSuccess(response.body()) ;//let presenter know the weather information data
                }
                else{
                    callback.onRequestFailed(response.message()) ;//let presenter know about failure
                }
            }

            @Override
            public void onFailure(Call<WeatherInfoResponse> call, Throwable t) {
                callback.onRequestFailed(t.getLocalizedMessage()); //let presenter know about failure
            }
        });
    }

    @Override
    public void getGeoDataInfo(String city, RequestCompleteListener<List<CityGeoData>> callback) {
        ApiInterface apiInterface  = RetrofitClient.getClient().create(ApiInterface.class);
        apiInterface.callGeoCoderApi(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<CityGeoData>>() {
                    @Override
                    public void accept(List<CityGeoData> list) throws Exception {
                        callback.onRequestSuccess(list);
                    }
                });
    }

    @Override
    public void getWeatherInfoWithGeoCoordinate(double lat, double lon, RequestCompleteListener<WeatherInfoResponse> callback) {
        ApiInterface apiInterface  = RetrofitClient.getClient().create(ApiInterface.class);
        apiInterface.callApiForWeatherInfoWithCoordinates(lat,lon)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherInfoResponse>() {
                    @Override
                    public void accept(WeatherInfoResponse list) throws Exception {
                        callback.onRequestSuccess(list);
                    }
                });
    }


}
