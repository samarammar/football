package com.glamera.football.data.factory

import com.glamera.football.data.source.EntityData
import com.glamera.football.data.source.network.NetworkEntityData
import com.glamera.football.util.Source
import javax.inject.Inject

class CompetitionFactory @Inject constructor(
    private val networkEntityData: NetworkEntityData
) {

    fun create(source: Source): EntityData {
        return networkEntityData
//        return when (source) {
//            Source.NETWORK -> networkEntityData
//            else -> networkEntityData
//        }
    }
}