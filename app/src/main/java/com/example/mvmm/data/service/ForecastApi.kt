package com.example.mvmm.data.service

import com.example.mvmm.data.model.forecast.Forecast
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApi {
    @GET("onecall")
fun getForecast(
    @Query("lat") lat:Double?,
    @Query("lon") lon:Double?,
    @Query("exclude") exclude:String,
    @Query("units") units:String,
    @Query("lang") lang:String,
    @Query("appid") appid:String,

    ) : Observable<Forecast>

}