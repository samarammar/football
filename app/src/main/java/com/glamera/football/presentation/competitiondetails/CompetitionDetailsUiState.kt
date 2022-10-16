package com.glamera.football.presentation.competitiondetails

import com.glamera.football.domain.entity.Area
import com.glamera.football.domain.entity.CurrentSeason

data class CompetitionDetailsUiState (
    val name:String?,val type:String?,val emblem:String?,val seasons:List<CurrentSeason>,
    val area: Area?

)