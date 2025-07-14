package com.nocholla.nyc.schools.hilt.retrofit.compose.ui.domain.usecase

import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.domain.model.School
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.domain.repository.SchoolRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSchoolsUseCase @Inject constructor(private val repository: SchoolRepository) {
    suspend operator fun invoke(): Result<Flow<List<School>>> {
        return try {
            val schoolsFlow = repository.getSchools().map { schools ->
                if (schools.isEmpty()) throw Exception("No schools found")
                schools
            }.catch { e -> throw e }
            Result.success(schoolsFlow)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}