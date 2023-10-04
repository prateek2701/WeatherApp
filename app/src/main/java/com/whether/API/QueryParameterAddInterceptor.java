package com.whether.API;

import com.whether.data.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class QueryParameterAddInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        HttpUrl url = chain.request().url().newBuilder()
                .addQueryParameter("appid", "f1458ad5626b200687d7f3a606529141")
                .build();

        Request request = chain.request().newBuilder()
                // .addHeader("Authorization", "Bearer token")
                .url(url)
                .build();

        return chain.proceed(request);
    }
}
