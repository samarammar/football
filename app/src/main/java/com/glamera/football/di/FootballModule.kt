package com.glamera.football.di

import com.glamera.football.data.repository.RepositoryImpl
import com.glamera.football.domain.repository.FootballRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class FootballModule {

    @Binds
    abstract fun bindScheduleRepository(repository: RepositoryImpl): FootballRepository
}