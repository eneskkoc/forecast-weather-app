package com.example.mvmm.data.database.dao

import androidx.room.*
import com.example.mvmm.data.database.entity.CoordiEntity

@Dao
interface CoordiDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCoordi(coordinate: CoordiEntity)

    @Query("SELECT * from coordinate")
    fun getLat(): List<CoordiEntity>

    @Query("SELECT * from coordinate where lower(name) = lower(:name) COllATE LOCALIZED")
    fun getName(name: String): List<CoordiEntity>

    @Query("DELETE  from coordinate where lower(name) = lower(:name) COllATE LOCALIZED")
    fun deleteCoordinate(name: String)




/*
     @Query("SELECT * from coordinate where lon= :lon ")
     fun getLon(lon:Double):List<CoordiEntity>



     */

    /*

    @Query("SELECT * FROM coordinate ORDER BY abs(lat-:lat) AND abs(lon-:lon) LIMIT 1")
    fun getCoordinate(lat: Double?, lon: Double?): List<Double>

     */
/*


    @Delete
    fun delete(coordinate: CoordiEntity)

 */

// hepsini silme parametresiz
     @Query("DELETE FROM coordinate")
     fun deleteAll()



}
