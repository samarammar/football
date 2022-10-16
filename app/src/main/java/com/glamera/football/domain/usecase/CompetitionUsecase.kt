package com.glamera.football.domain.usecase

import com.glamera.football.domain.entity.CompetitionItem
import com.glamera.football.domain.repository.FootballRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CompetitionUsecase @Inject constructor(
    private val footballRepository: FootballRepository
) {

    suspend fun execute(): Flow<List<CompetitionItem>> {
        return footballRepository.getCompetitionsList()
    }
}