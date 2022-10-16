package com.glamera.football.data.source.network

import com.glamera.football.data.api.ApiService
import com.glamera.football.data.model.CompetitionDetailsResponce
import com.glamera.football.data.model.TeamsResponce
import com.glamera.football.domain.entity.CompetitionItem
import com.glamera.football.data.source.EntityData
import com.glamera.football.domain.entity.TeamsItem
import javax.inject.Inject

class NetworkEntityData  @Inject constructor(
    private val apiService: ApiService
) : EntityData {

    override suspend fun getCompetetions(): List<CompetitionItem> {
        return apiService.getCompetitions().competitions!!
    }

    override suspend fun getCompetitionDetails( comepetId: String): CompetitionDetailsResponce{
        return apiService.getCompetitionDetails(comepetId)
    }

    override suspend fun getTeams(comepetId: String):  List<TeamsItem>{
        return apiService.getTeams(comepetId).teams!!
    }
}