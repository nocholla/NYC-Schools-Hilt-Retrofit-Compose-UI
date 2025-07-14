package com.nocholla.nyc.schools.hilt.retrofit.compose.ui.domain.usecase

import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.domain.model.Score
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.domain.repository.SchoolRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetScoresUseCase @Inject constructor(private val repository: SchoolRepository) {
    suspend operator fun invoke(): Result<Flow<List<Score>>> {
        return try {
            val scoresFlow = repository.getScores().map { scores ->
                if (scores.isEmpty()) throw Exception("No scores found")
                scores
            }.catch { e -> throw e }
            Result.success(scoresFlow)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}