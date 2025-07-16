package com.nocholla.nyc.schools.hilt.retrofit.compose.ui.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.domain.model.Score

@Entity(tableName = "scores")
data class ScoreEntity(
    @PrimaryKey val dbn: String,
    val schoolName: String?,
    val numOfSatTestTakers: String?,
    val satCriticalReadingAvgScore: String?,
    val satMathAvgScore: String?,
    val satWritingAvgScore: String?
) {
    fun toDomain() = Score(
        dbn = dbn,
        schoolName = schoolName,
        numOfSatTestTakers = numOfSatTestTakers,
        satCriticalReadingAvgScore = satCriticalReadingAvgScore,
        satMathAvgScore = satMathAvgScore,
        satWritingAvgScore = satWritingAvgScore
    )
}