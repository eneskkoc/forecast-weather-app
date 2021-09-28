package com.example.mvmm.data.model.forecast

import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

data class Daily(
    //val clouds: Int?,
    //val dew_point: Double?,
    @SerializedName("dt")
    val dt: Long?,
    val feels_like: FeelsLike?,
    @SerializedName("humidity")
    val humidity: Int?,
    //val moon_phase: Double?,
    //val moonrise: Int?,
    //val moonset: Int?,
    //val pop: Int?,
    //val pressure: Int?,
    //val rain: Int?,
    //val sunrise: Int?,
    //val sunset: Int?,
    @SerializedName("temp")
    val temp: Temp?,
    //val uvi: Double?,
    @SerializedName("weather")
    val weather: List<WeatherX>?,
    //val wind_deg: Int?,
    //val wind_gust: Double?,
    //val wind_speed: Double?
) {
    @SuppressLint("SimpleDateFormat")
    fun date(): String { // timestamp convert date
        var day = dt
        val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("tr"))
        var dy = simpleDateFormat.format(day!! * 1000L)
        return dy.toString()
    }
}