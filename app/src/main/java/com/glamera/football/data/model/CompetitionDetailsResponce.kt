package com.glamera.football.data.model

import com.glamera.football.domain.entity.CompetitionItem
import com.glamera.football.domain.entity.CurrentSeason

data class CompetitionDetailsResponce (
val name:String?,val type:String?,val emblem:String?,val seasons:List<CurrentSeason>
)