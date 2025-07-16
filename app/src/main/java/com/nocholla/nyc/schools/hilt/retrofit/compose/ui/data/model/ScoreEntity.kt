package com.nocholla.nyc.schools.hilt.retrofit.compose.ui.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scores")
data class ScoreEntity(
    @PrimaryKey val dbn: String,
    val schoolName: String?,
    val numOfSatTestTakers: String?,
    val satCriticalReadingAvgScore: String?,
    val satMathAvgScore: String?,
    val satWritingAvgScore: String?
) {
    fun toDomain() = com.nocholla.nyc.schools.hilt.retrofit.compose.ui.domain.model.Score(
        dbn = dbn,
        schoolName = schoolName,
        numOfSatTestTakers = numOfSatTestTakers,
        satCriticalReadingAvgScore = satCriticalReadingAvgScore,
        satMathAvgScore = satMathAvgScore,
        satWritingAvgScore = satWritingAvgScore
    )
}