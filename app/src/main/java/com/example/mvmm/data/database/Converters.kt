package com.example.mvmm.data.database

import androidx.room.TypeConverter
import com.example.mvmm.data.database.entity.*
import com.example.mvmm.data.model.forecast.Weather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromWetherToGesonx(list :List<WeatherEntity>) : String{
        return Gson().toJson(list)
    }
    @TypeConverter
    fun fromJsonToWeatherx(gson: String):List<WeatherEntity>{
        val type = object : TypeToken<List<WeatherEntity>>() {}.type
        return Gson().fromJson(gson,type)
    }

    @TypeConverter
    fun fromWetherToGesonxx(list :List<WeatherXentity>) : String{
        return Gson().toJson(list)
    }
    @TypeConverter
    fun fromJsonToWeatherxx(gson: String):List<WeatherXentity>{
        val type = object : TypeToken<List<WeatherXentity>>() {}.type
        return Gson().fromJson(gson,type)
    }
    @TypeConverter
    fun fromDailyToGesonxx(list :List<DailyEntity>) : String{
        return Gson().toJson(list)
    }
    @TypeConverter
    fun fromJsonToDailyxx(gson: String):List<DailyEntity>{
        val type = object : TypeToken<List<DailyEntity>>() {}.type
        return Gson().fromJson(gson,type)
    }
    @TypeConverter
    fun fromTempToGesonxx(list : TempEntity) : String{
        return Gson().toJson(list)
    }
    @TypeConverter
    fun fromJsonToTempxx(gson: String):TempEntity{
        val type = object : TypeToken<TempEntity>() {}.type
        return Gson().fromJson(gson,type)
    }
    @TypeConverter
    fun fromCurrentToGesonxx(list : CurrentEntity) : String{
        return Gson().toJson(list)
    }
    @TypeConverter
    fun fromJsonToCurrentxx(gson: String):CurrentEntity{
        val type = object : TypeToken<CurrentEntity>() {}.type
        return Gson().fromJson(gson,type)
    }


}

