package com.glamera.football.data.source.network

import com.glamera.football.data.api.ApiService
import com.glamera.football.domain.entity.CompetitionItem
import com.glamera.football.data.source.EntityData
import javax.inject.Inject

class NetworkEntityData  @Inject constructor(
    private val apiService: ApiService
) : EntityData {

    override suspend fun getCompetetions(): List<CompetitionItem> {
        return apiService.getCompetitions().competitions
    }
}