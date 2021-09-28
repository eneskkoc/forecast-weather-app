package com.example.mvmm.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.math.roundToInt

@Entity(tableName = "temp")
data class TempEntity (

    @ColumnInfo(name = "max") var max: Double?,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int=0

    ) {
    fun tempRound() = max?.roundToInt()
}

