package com.glamera.football.data.source

import com.glamera.football.domain.entity.CompetitionItem

interface EntityData {
    suspend fun getCompetetions(): List<CompetitionItem>

}