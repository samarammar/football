package com.glamera.football.domain.usecase

import com.glamera.football.data.model.CompetitionDetailsResponce
import com.glamera.football.domain.entity.CompetitionItem
import com.glamera.football.domain.repository.FootballRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CompetitionDetailUsecase @Inject constructor(
    private val footballRepository: FootballRepository
) {

    suspend fun execute(competId:String): Flow<CompetitionDetailsResponce> {
        return footballRepository.getCompetitionDetails(competId)
    }

}