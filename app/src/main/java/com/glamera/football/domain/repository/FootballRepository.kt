package com.glamera.football.domain.repository

import com.glamera.football.data.model.CompetitionDetailsResponce
import com.glamera.football.data.model.TeamsResponce
import com.glamera.football.domain.entity.CompetitionItem
import com.glamera.football.domain.entity.TeamsItem
import kotlinx.coroutines.flow.Flow

interface FootballRepository {
    suspend fun getCompetitionsList(): Flow<List<CompetitionItem>>
    suspend fun getCompetitionDetails(comepetId: String): Flow<CompetitionDetailsResponce>
    suspend fun getTeams(comepetId: String): Flow<List<TeamsItem>>
}