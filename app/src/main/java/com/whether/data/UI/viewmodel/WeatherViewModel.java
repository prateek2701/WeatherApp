package com.whether.data.UI.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.whether.data.RequestCompleteListener;
import com.whether.data.UI.model.WeatherInfoShowModel;
import com.whether.data.UI.model.data.City;
import com.whether.data.UI.model.data.CityGeoData;
import com.whether.data.UI.model.data.GeoData;
import com.whether.data.UI.model.data.WeatherData;
import com.whether.data.UI.model.data.WeatherInfoResponse;

import java.util.List;

public class WeatherViewModel extends ViewModel {
    public MutableLiveData<List<City>> cityListLiveData = new MutableLiveData<>();
    public MutableLiveData<String> cityListFailureLiveData= new MutableLiveData<>();
    public MutableLiveData<WeatherData> weatherInfoLiveData= new MutableLiveData<>();
    public MutableLiveData<String> weatherInfoFailureLiveData= new MutableLiveData<>();
    public MutableLiveData<Boolean> progressBarLiveData= new MutableLiveData<>();

    public MutableLiveData<List<CityGeoData>>geoCoderLiveData = new MutableLiveData<>();

    /**
     * We can inject the instance of Model in Constructor using dependency injection.
     * For sake of simplicity, I am ignoring it now. So we have to pass instance of model in every
     * methods of ViewModel. Please be noted, it's not a good approach.
     */
    public void getCityList(WeatherInfoShowModel model) {
        model.getCityList(new RequestCompleteListener<List<City>>() {
            @Override
            public void onRequestSuccess(List<City> data) {
                cityListLiveData.postValue(data);

            }

            @Override
            public void onRequestFailed(String errorMessage) {
                cityListFailureLiveData.postValue(errorMessage);
            }
        });
    }

    /**
     * We can inject the instance of Model in Constructor using dependency injection.
     * For sake of simplicity, I am ignoring it now. So we have to pass instance of model in every
     * methods of ViewModel. Pleas be noted, it's not a good approach.
     */

    public void getWeatherInfo(String city, WeatherInfoShowModel model) {

        progressBarLiveData.postValue(true); // PUSH data to LiveData object to show progress bar
        model.getWeatherInfo(city, new RequestCompleteListener<WeatherInfoResponse>() {
            @Override
            public void onRequestSuccess(WeatherInfoResponse data) {
                int dateTime = data.getDt();
                String temperature = data.getMain().getTemp().toString();
                String cityAndCountry = data.getName() +"-" +data.getSys().getCountry();// "${data.name}, ${data.sys.country}";
                String weatherConditionIconUrl = "https://openweathermap.org/img/w/" + data.getWeather().get(0).getIcon() +".png";
                String weatherConditionIconDescription = data.getWeather().get(0).getDescription();
                String humidity = String.valueOf(data.getMain().getHumidity());//"${data.main.humidity}%";
                String pressure = String.valueOf(data.getMain().getPressure()); //"${data.main.pressure} mBar";
//                String visibility = String.valueOf(data.getMain().()) "${data.visibility/1000.0} KM";
                int sunrise = data.getSys().getSunrise();
                int sunset = data.getSys().getSunset();
                WeatherData weatherData = new WeatherData(dateTime,temperature,cityAndCountry,weatherConditionIconUrl,
                        weatherConditionIconDescription,humidity,pressure,
                        sunrise,sunset
                        );
                progressBarLiveData.postValue(false); // PUSH data to LiveData object to hide progress bar

                // After applying business logic and data manipulation, we push data to show on UI
                weatherInfoLiveData.postValue(weatherData);
            }

            @Override
            public void onRequestFailed(String errorMessage) {
                progressBarLiveData.postValue(false); // hide progress bar
                weatherInfoFailureLiveData.postValue(errorMessage); // PUSH error message to LiveData object

            }
        });

    }

    public void getGeoLocationData(String city,WeatherInfoShowModel model){
        progressBarLiveData.postValue(true); // PUSH data to LiveData object to show progress bar

        model.getGeoDataInfo(city, new RequestCompleteListener<List<CityGeoData>>() {
            @Override
            public void onRequestSuccess(List<CityGeoData> data) {
                progressBarLiveData.postValue(false);
                geoCoderLiveData.postValue(data);
            }

            @Override
            public void onRequestFailed(String errorMessage) {
                progressBarLiveData.postValue(false); // hide progress bar
                weatherInfoFailureLiveData.postValue(errorMessage);
            }
        });
    }

    public void getWeatherInfoWithGeoData(double lat,double lon,WeatherInfoShowModel model){
        progressBarLiveData.postValue(true);
        model.getWeatherInfoWithGeoCoordinate(lat, lon, new RequestCompleteListener<WeatherInfoResponse>() {
            @Override
            public void onRequestSuccess(WeatherInfoResponse data) {
                progressBarLiveData.postValue(false);
                int dateTime = data.getDt();
                String temperature = data.getMain().getTemp().toString();
                String cityAndCountry = data.getName() +"-" +data.getSys().getCountry();// "${data.name}, ${data.sys.country}";
                String weatherConditionIconUrl = "https://openweathermap.org/img/w/" + data.getWeather().get(0).getIcon() +".png";
                String weatherConditionIconDescription = data.getWeather().get(0).getDescription();
                String humidity = String.valueOf(data.getMain().getHumidity());//"${data.main.humidity}%";
                String pressure = String.valueOf(data.getMain().getPressure()); //"${data.main.pressure} mBar";
//                String visibility = String.valueOf(data.getMain().()) "${data.visibility/1000.0} KM";
                int sunrise = data.getSys().getSunrise();
                int sunset = data.getSys().getSunset();
                WeatherData weatherData = new WeatherData(dateTime,temperature,cityAndCountry,weatherConditionIconUrl,
                        weatherConditionIconDescription,humidity,pressure,
                        sunrise,sunset
                );

                weatherInfoLiveData.postValue(weatherData);
            }

            @Override
            public void onRequestFailed(String errorMessage) {
                progressBarLiveData.postValue(false);
                weatherInfoFailureLiveData.postValue(errorMessage);
            }
        });
    }
}
