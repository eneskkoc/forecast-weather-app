package com.example.mvmm.data.service

import com.example.mvmm.data.model.geo.GeoItem
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiGeo{
    @GET("direct")
    fun getGeo(
        @Query("q") city: String, // şehir kısmı
        @Query("limit") limit: String, // api key
        @Query("appid") authorizationKey: String // api key

    ): Observable<ArrayList<GeoItem>>

}