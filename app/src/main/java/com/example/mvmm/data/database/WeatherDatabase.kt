package com.example.mvmm.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mvmm.data.database.dao.*
import com.example.mvmm.data.database.entity.*

@Database(
    entities = [
        CoordiEntity::class,
        CurrentEntity::class,
        ForecastEntity::class,
        DailyEntity::class,
        TempEntity::class,
        WeatherEntity::class,
        WeatherXentity::class
    ],
    version = 1
)
@TypeConverters(Converters::class)

abstract class WeatherDatabase : RoomDatabase() {
    abstract fun forecastDao(): ForecastDao
    abstract fun coordinateDao(): CoordiDao
    abstract fun currentDao(): CurrentDao
    abstract fun dailyDao(): DailyDao
    abstract fun tempDao(): TempDao
    abstract fun weatherDao(): WeatherDao
    abstract fun weatherXdao(): WeatherXdao


}
