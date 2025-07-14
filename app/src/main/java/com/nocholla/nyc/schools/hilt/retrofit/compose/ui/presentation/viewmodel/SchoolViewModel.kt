package com.nocholla.nyc.schools.hilt.retrofit.compose.ui.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.domain.model.School
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.domain.model.Score
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.domain.usecase.GetSchoolsUseCase
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.domain.usecase.GetScoresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import retrofit2.HttpException

data class SchoolUiState(
    val schools: List<School> = emptyList(),
    val scores: List<Score> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class SchoolViewModel @Inject constructor(
    private val getSchoolsUseCase: GetSchoolsUseCase,
    private val getScoresUseCase: GetScoresUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SchoolUiState(isLoading = true))
    val uiState: StateFlow<SchoolUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            getSchoolsUseCase().onSuccess { schoolsFlow ->
                schoolsFlow.collect { schools ->
                    _uiState.update { it.copy(schools = schools, isLoading = false) }
                }
            }.onFailure { e ->
                _uiState.update {
                    it.copy(
                        error = when (e) {
                            is HttpException -> "API Error: ${e.code()} - ${e.message()}"
                            else -> e.localizedMessage ?: "Unknown error"
                        },
                        isLoading = false
                    )
                }
            }
            getScoresUseCase().onSuccess { scoresFlow ->
                scoresFlow.collect { scores ->
                    _uiState.update { it.copy(scores = scores, isLoading = false) }
                }
            }.onFailure { e ->
                _uiState.update {
                    it.copy(
                        error = when (e) {
                            is HttpException -> "API Error: ${e.code()} - ${e.message()}"
                            else -> e.localizedMessage ?: "Unknown error"
                        },
                        isLoading = false
                    )
                }
            }
        }
    }

    fun getSchoolByDbn(dbn: String?): School? {
        return uiState.value.schools.find { it.dbn == dbn }
    }
}