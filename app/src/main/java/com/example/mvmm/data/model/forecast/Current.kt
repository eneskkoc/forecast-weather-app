package com.example.mvmm.data.model.forecast

import com.google.gson.annotations.SerializedName

data class Current(
    //val clouds: Int?,
    //val dew_point: Double?,
    @SerializedName("dt")
    val dt: Long?,
    //val feels_like: Double?,
    @SerializedName("humidity")
    val humidity: Int?,
    //val pressure: Int?,
    //val rain: Rain?,
    //val sunrise: Int?,
    //val sunset: Int?,
    @SerializedName("temp")
    val temp: Double?,
    //val uvi: Int?,
    //val visibility: Int?,
    @SerializedName("weather")
    val weather: List<Weather>?,
    //val wind_deg: Int?,
    //val wind_speed: Double?
)