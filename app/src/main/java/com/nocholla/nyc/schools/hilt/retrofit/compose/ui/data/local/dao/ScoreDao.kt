package com.nocholla.nyc.schools.hilt.retrofit.compose.ui.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.data.local.model.ScoreEntity

@Dao
interface ScoreDao {
    @Query("SELECT * FROM scores")
    suspend fun getAllScores(): List<ScoreEntity>

    @Query("SELECT * FROM scores WHERE dbn = :dbn")
    suspend fun getScoreByDbn(dbn: String): ScoreEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllScores(scores: List<ScoreEntity>)

    @Query("DELETE FROM scores")
    suspend fun deleteAllScores()
}