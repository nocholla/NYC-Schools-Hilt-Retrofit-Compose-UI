package com.nocholla.nyc.schools.hilt.retrofit.compose.ui.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.data.local.model.SchoolEntity

@Dao
interface SchoolDao {
    @Query("SELECT * FROM schools")
    suspend fun getAllSchools(): List<SchoolEntity>

    @Query("SELECT * FROM schools WHERE dbn = :dbn")
    suspend fun getSchoolByDbn(dbn: String): SchoolEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSchools(schools: List<SchoolEntity>)

    @Query("DELETE FROM schools")
    suspend fun deleteAllSchools()
}