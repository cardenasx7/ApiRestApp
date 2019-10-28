package com.example.apirestapp.retrofit;

import com.example.apirestapp.BuildConfig;
import com.example.apirestapp.Constantes;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
        baseUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";



        HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor().
                        setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        OkHttpClient okHttpClient =
                new OkHttpClient.Builder()
                        .addInterceptor(httpLoggingInterceptor)
                        .connectTimeout(Constantes.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                        .writeTimeout(Constantes.WRITE_TIMEOUT, TimeUnit.SECONDS)
                        .readTimeout(Constantes.READ_TIMEOUT, TimeUnit.SECONDS)
                        .build();

        Retrofit provideRetrofitClient
                = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create()) // Serialize Objects
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //Set call to return {@link Observable}
                .build();

        return provideRetrofitClient.create(serviceClass);
    }
}
