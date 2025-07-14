package com.nocholla.nyc.schools.hilt.retrofit.compose.ui.data.model

import com.google.gson.annotations.SerializedName
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.domain.model.Score

data class ScoreDto(
    @SerializedName("dbn") val dbn: String?,
    @SerializedName("school_name") val schoolName: String?,
    @SerializedName("num_of_sat_test_takers") val numOfSatTestTakers: String?,
    @SerializedName("sat_critical_reading_avg_score") val satCriticalReadingAvgScore: String?,
    @SerializedName("sat_math_avg_score") val satMathAvgScore: String?,
    @SerializedName("sat_writing_avg_score") val satWritingAvgScore: String?
) {
    fun toDomain() = Score(
        dbn = dbn ?: "",
        schoolName = schoolName ?: "",
        numOfSatTestTakers = numOfSatTestTakers ?: "",
        satCriticalReadingAvgScore = satCriticalReadingAvgScore ?: "",
        satMathAvgScore = satMathAvgScore ?: "",
        satWritingAvgScore = satWritingAvgScore ?: ""
    )
}