package com.glamera.football.domain.repository

import com.glamera.football.domain.entity.CompetitionItem
import kotlinx.coroutines.flow.Flow

interface FootballRepository {
    suspend fun getCompetitionsList(): Flow<List<CompetitionItem>>

}