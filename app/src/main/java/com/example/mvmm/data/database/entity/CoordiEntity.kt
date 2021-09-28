package com.example.mvmm.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "coordinate")
data class CoordiEntity(

    @ColumnInfo(name = "lat") var lat: Double?,
    @ColumnInfo(name = "lon") var lon: Double?,
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "time") var time :Long?,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int=0

)
