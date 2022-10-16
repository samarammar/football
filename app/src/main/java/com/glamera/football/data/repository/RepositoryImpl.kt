package com.glamera.football.data.repository

import com.glamera.football.data.factory.CompetitionFactory
import com.glamera.football.data.model.CompetitionDetailsResponce
import com.glamera.football.domain.entity.CompetitionItem
import com.glamera.football.domain.repository.FootballRepository
import com.glamera.football.util.Source
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val competitionFactory: CompetitionFactory

) : FootballRepository {


    override suspend fun getCompetitionsList(): Flow<List<CompetitionItem>> {
        return flow{
            emit( competitionFactory.create(Source.NETWORK).getCompetetions())
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getCompetitionDetails(comepetId: String): Flow<CompetitionDetailsResponce> {
        return flow{
            emit( competitionFactory.create(Source.NETWORK).getCompetitionDetails(comepetId))
        }.flowOn(Dispatchers.IO)
    }


}