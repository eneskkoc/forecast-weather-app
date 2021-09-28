package com.example.mvmm.data.database.dao

import androidx.room.*
import com.example.mvmm.data.database.entity.CurrentEntity

@Dao
interface CurrentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrent(current: CurrentEntity)

    @Query("SELECT * from current")
    fun getCurrent(): List<CurrentEntity>

    @Query("DELETE FROM current")
    fun deleteAll()
/*
    @Update
    fun updateCurrent(current: CurrentEntity)

 */
}