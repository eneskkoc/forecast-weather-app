package com.example.mvmm.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvmm.data.database.entity.WeatherEntity

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(weather: WeatherEntity)

    @Query("SELECT * from weather")
    fun getWeather(): List<WeatherEntity>

    @Query("DELETE FROM weather")
    fun deleteAll()
/*
    @Update
    fun updateWeather(weather: WeatherEntity)

 */
}