package com.glamera.football.presentation.competitiondetails

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glamera.football.data.networking.CoroutineDispatcherProvider
import com.glamera.football.domain.usecase.CompetitionDetailUsecase
import com.glamera.football.domain.usecase.CompetitionUsecase
import com.glamera.football.domain.usecase.TeamsUsecase
import com.glamera.football.presentation.competitions.CompetitionUiState
import com.glamera.football.presentation.competitions.CompetitionsViewModel
import com.glamera.football.util.ExceptionParser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompetitionDetailsViewModel @Inject
constructor(
    private val competitionDetailUsecase: CompetitionDetailUsecase,
    private val teamsUsecase: TeamsUsecase,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
) : ViewModel() {
    private val _uiState = MutableStateFlow<FootballDetailUiState>(
        FootballDetailUiState.Loading)
    val uiState: StateFlow<FootballDetailUiState> = _uiState


    fun getCompetitionsDetails(competId:String) {
        _uiState.value = FootballDetailUiState.Loading

        viewModelScope.launch(coroutineDispatcherProvider.IO()) {
            try {

                val result = competitionDetailUsecase.execute(competId)

                _uiState.value = FootballDetailUiState.
                Loaded(CompetitionDetailsUiState(result.first().name,result.first().type,
                    result.first().emblem,result.first().seasons,result.first().area))
            } catch (error: Exception) {
                _uiState.value = FootballDetailUiState.Error(ExceptionParser.getMessage(error))
            }
        }
    }



    sealed class FootballDetailUiState {
        object Loading : FootballDetailUiState()
        class Loaded(val itemState: CompetitionDetailsUiState) : FootballDetailUiState()
        class Error(@StringRes val message: Int) : FootballDetailUiState()
    }
}
