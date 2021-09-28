package com.example.mvmm.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "forecast")

data class ForecastEntity(

    @ColumnInfo(name = "current") var current:CurrentEntity?,//CurrentEntity
    //@ColumnInfo(name = "lat") var lat: Double?,
    //@ColumnInfo(name = "lon") var lon: Double?,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int = 0
)

