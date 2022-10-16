package com.glamera.football.presentation.competitions

import com.glamera.football.domain.entity.CompetitionItem
import kotlinx.coroutines.flow.Flow

data class CompetitionUiState (

    val competitions: Flow<List<CompetitionItem>>

)