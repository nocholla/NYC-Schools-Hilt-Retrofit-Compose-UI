package com.nocholla.nyc.schools.hilt.retrofit.compose.ui.data.api

import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.data.model.SchoolDto
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.data.model.ScoreDto
import retrofit2.http.GET

interface SchoolsApiService {
    @GET("s3k6-pzi2.json")
    suspend fun getSchools(): List<SchoolDto>

    @GET("f9bf-2cp4.json")
    suspend fun getScores(): List<ScoreDto>
}

// Schools JSON
// https://data.cityofnewyork.us/resource/s3k6-pzi2.json

// Scores JSON
// https://data.cityofnewyork.us/resource/f9bf-2cp4.json