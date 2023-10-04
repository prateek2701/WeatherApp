package com.whether.data.UI.model.data;

public class WeatherData {
    public int getDateTime() {
        return dateTime;
    }

    public void setDateTime(int dateTime) {
        this.dateTime = dateTime;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getCityAndCountry() {
        return cityAndCountry;
    }

    public void setCityAndCountry(String cityAndCountry) {
        this.cityAndCountry = cityAndCountry;
    }

    public String getWeatherConditionIconUrl() {
        return weatherConditionIconUrl;
    }

    public void setWeatherConditionIconUrl(String weatherConditionIconUrl) {
        this.weatherConditionIconUrl = weatherConditionIconUrl;
    }

    public String getWeatherConditionIconDescription() {
        return weatherConditionIconDescription;
    }

    public void setWeatherConditionIconDescription(String weatherConditionIconDescription) {
        this.weatherConditionIconDescription = weatherConditionIconDescription;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public int getSunrise() {
        return sunrise;
    }

    public void setSunrise(int sunrise) {
        this.sunrise = sunrise;
    }

    public int getSunset() {
        return sunset;
    }

    public void setSunset(int sunset) {
        this.sunset = sunset;
    }

    int dateTime ;
    String temperature ;
    String cityAndCountry;
    String weatherConditionIconUrl;
    String weatherConditionIconDescription;
    String humidity;
    String pressure;
    String visibility;
    int sunrise ;
    int sunset;

    public WeatherData(int dateTime, String temperature, String cityAndCountry, String weatherConditionIconUrl, String weatherConditionIconDescription, String humidity, String pressure, int sunrise, int sunset) {
        this.dateTime = dateTime;
        this.temperature = temperature;
        this.cityAndCountry = cityAndCountry;
        this.weatherConditionIconUrl = weatherConditionIconUrl;
        this.weatherConditionIconDescription = weatherConditionIconDescription;
        this.humidity = humidity;
        this.pressure = pressure;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }


}
