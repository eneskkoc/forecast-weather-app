package com.example.mvmm.data.database

import android.app.Application
import androidx.room.Room
import com.example.mvmm.di.PerApplication
import dagger.Module
import dagger.Provides

@Module
class DBModule {
    @PerApplication
    @Provides
    fun database(application: Application) = Room.databaseBuilder(
        application.applicationContext,
        WeatherDatabase::class.java, "weather"
    )
        .build()
}