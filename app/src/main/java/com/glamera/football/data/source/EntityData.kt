package com.glamera.football.data.source

import com.glamera.football.data.model.CompetitionDetailsResponce
import com.glamera.football.data.model.TeamsResponce
import com.glamera.football.domain.entity.CompetitionItem
import com.glamera.football.domain.entity.TeamsItem

interface EntityData {
    suspend fun getCompetetions(): List<CompetitionItem>
    suspend fun getCompetitionDetails(comepetId: String): CompetitionDetailsResponce
    suspend fun getTeams(comepetId: String): List<TeamsItem>
}