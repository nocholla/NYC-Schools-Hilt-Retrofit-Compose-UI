package com.nocholla.nyc.schools.hilt.retrofit.compose.ui.domain.repository

import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.domain.model.School
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.domain.model.Score
import kotlinx.coroutines.flow.Flow

interface SchoolRepository {
    suspend fun getSchools(): Flow<List<School>>
    suspend fun getScores(): Flow<List<Score>>
}