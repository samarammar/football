package com.glamera.football.data.api

import com.glamera.football.data.model.CompetitionDetailsResponce
import com.glamera.football.data.model.CompetitionsResponce
import com.glamera.football.data.model.TeamsResponce
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {

    @Headers("X-Auth-Token: 9b0d29d475c04224893e76606baa220a")
    @GET("competitions/")
    suspend fun getCompetitions(): CompetitionsResponce

    @Headers("X-Auth-Token: 9b0d29d475c04224893e76606baa220a")
    @GET("competitions/{id}/")
    suspend fun getCompetitionDetails(@Path("id") comepetId: String): CompetitionDetailsResponce

    @Headers("X-Auth-Token: 9b0d29d475c04224893e76606baa220a")
    @GET("competitions/{id}/teams/")
    suspend fun getTeams(@Path("id") comepetId: String): TeamsResponce
}