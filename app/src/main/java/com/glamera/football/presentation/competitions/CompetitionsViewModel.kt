package com.glamera.football.presentation.competitions

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glamera.football.data.networking.CoroutineDispatcherProvider
import com.glamera.football.domain.usecase.CompetitionUsecase
import com.glamera.football.util.ExceptionParser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompetitionsViewModel @Inject
constructor(
    private val competitionUsecase: CompetitionUsecase,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow<FootballUiState>(FootballUiState.Loading)
    val uiState: StateFlow<FootballUiState> = _uiState


    fun getCompetitionsList() {
        _uiState.value = FootballUiState.Loading

        viewModelScope.launch(coroutineDispatcherProvider.IO()) {
            try {

                val result = competitionUsecase.execute()

                _uiState.value = FootballUiState.Loaded(CompetitionUiState(result))
            } catch (error: Exception) {
                _uiState.value = FootballUiState.Error(ExceptionParser.getMessage(error))
            }
        }
    }



    sealed class FootballUiState {
        object Loading : FootballUiState()
        class Loaded(val itemState: CompetitionUiState) : FootballUiState()
        class Error(@StringRes val message: Int) : FootballUiState()
    }
}