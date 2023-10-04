package com.whether.data;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {

    private static PreferenceHelper myPreferences;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    private PreferenceHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(Config.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    public static PreferenceHelper getPreferences(Context context) {
        if (myPreferences == null) myPreferences = new PreferenceHelper(context);
        return myPreferences;
    }

    public void setLastCity(String cityName){
        editor.putString(Config.LAST_CITY, cityName);
        editor.apply();
    }

    public String getLastCity(){
        //if no data is available for Config.USER_NAME then this getString() method returns
        //a default value that is mentioned in second parameter
        return sharedPreferences.getString(Config.LAST_CITY, "");
    }

    public void setLat(Double lat){
        editor.putString(Config.LAT, String.valueOf(lat));
        editor.apply();
    }

    public String getLLat(){
        //if no data is available for Config.USER_NAME then this getString() method returns
        //a default value that is mentioned in second parameter
        return sharedPreferences.getString(Config.LAT, "");
    }

    public void setLon(Double lon){
        editor.putString(Config.LON, String.valueOf(lon));
        editor.apply();
    }

    public String getLon(){
        //if no data is available for Config.USER_NAME then this getString() method returns
        //a default value that is mentioned in second parameter
        return sharedPreferences.getString(Config.LON, "");
    }

}
