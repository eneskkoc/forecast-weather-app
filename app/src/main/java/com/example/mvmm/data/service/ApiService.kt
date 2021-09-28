package com.example.mvmm.data.service

import android.app.Application
import com.example.mvmm.data.database.ConnectivityInterceptor
import com.example.mvmm.di.PerApplication
import com.example.mvmm.utils.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class ApiService {


    @Provides
    @PerApplication
    fun providesConnectivityInterceptor(application:Application): ConnectivityInterceptor {
        return ConnectivityInterceptor(application)
    }

    @Provides
    @PerApplication
    fun provideGson(): Gson {
        return GsonBuilder()
            .create()
    }

    @Provides
    @PerApplication
    fun provideOkHttpClient(connectivityInterceptor: ConnectivityInterceptor): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(connectivityInterceptor)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @PerApplication
    fun provideApiService(gson: Gson, okHttpClient: OkHttpClient): ApiGeo {
        return Retrofit.Builder()
            .baseUrl(Constants.API_BASE_PATH)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build().create(ApiGeo::class.java)
    }

    @Provides
    @PerApplication
    fun provideForecastService(gson: Gson, okHttpClient: OkHttpClient): ForecastApi {
        return Retrofit.Builder()
            .baseUrl(Constants.API_FORECAST_PATH)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build().create(ForecastApi::class.java)
    }

}