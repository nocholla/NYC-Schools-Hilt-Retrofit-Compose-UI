package com.nocholla.nyc.schools.hilt.retrofit.compose.ui.data.repository

import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.data.api.SchoolsApiService
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.domain.repository.SchoolRepository
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.domain.model.School
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.domain.model.Score
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SchoolRepositoryImpl @Inject constructor(
    private val schoolsApiService: SchoolsApiService
) : SchoolRepository {

    override suspend fun getSchools(): Flow<List<School>> = flow {
        val schoolDtos = schoolsApiService.getSchools()
        emit(schoolDtos.map { it.toDomain() })
    }

    override suspend fun getScores(): Flow<List<Score>> = flow {
        val scoreDtos = schoolsApiService.getScores()
        emit(scoreDtos.map { it.toDomain() })
    }
}