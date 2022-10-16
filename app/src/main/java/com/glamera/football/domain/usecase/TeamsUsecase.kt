package com.glamera.football.domain.usecase

import com.glamera.football.domain.entity.CompetitionItem
import com.glamera.football.domain.entity.TeamsItem
import com.glamera.football.domain.repository.FootballRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TeamsUsecase @Inject constructor(
    private val footballRepository: FootballRepository
) {

    suspend fun execute(competId:String): Flow<List<TeamsItem>> {
        return footballRepository.getTeams(competId)
    }

}