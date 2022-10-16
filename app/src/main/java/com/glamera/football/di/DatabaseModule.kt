package com.glamera.football.di

import android.content.Context
import androidx.room.Room
import com.glamera.football.data.database.MyRoomDatabase
import com.glamera.football.data.database.dao.TeamDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val DB_NAME = "Favourite"

    @Provides
    fun provideMyRoomDatabase(@ApplicationContext context: Context): MyRoomDatabase {
        return Room.databaseBuilder(
            context,
            MyRoomDatabase::class.java,
            DB_NAME
        ).build()
    }

    @Provides
    fun providePrayDao(myRoomDatabase: MyRoomDatabase): TeamDao {
        return myRoomDatabase.teamDao()
    }
}