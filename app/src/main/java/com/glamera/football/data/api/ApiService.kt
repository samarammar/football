package com.glamera.football.data.api

import com.glamera.football.data.model.CompetitionDetailsResponce
import com.glamera.football.data.model.CompetitionsResponce
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("competitions/")
    suspend fun getCompetitions(): CompetitionsResponce

    @GET("competitions/{id}/")
    suspend fun getCompetitionDetails(@Path("id") comepetId: String): CompetitionDetailsResponce
}