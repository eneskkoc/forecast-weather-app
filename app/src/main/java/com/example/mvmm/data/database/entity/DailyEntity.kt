package com.example.mvmm.data.database.entity

import android.annotation.SuppressLint
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "daily")
data class DailyEntity (

    @ColumnInfo(name = "dt") var dt: Long?,
    @ColumnInfo(name = "temp") var temp: TempEntity?,//TempEntity
    @ColumnInfo(name = "weather") var weather: List<WeatherXentity>?,//List<Weather>
    @ColumnInfo(name = "humidity") var humidity: Int?,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int=0

    )
{
    @SuppressLint("SimpleDateFormat")
    fun date(): String { // timestamp convert date
        var day=dt
        val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("tr"))
        var dy=simpleDateFormat.format(day!! * 1000L)
        return dy.toString()

    }

}

