package com.glamera.football.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.glamera.football.data.database.dao.TeamDao
import com.glamera.football.data.database.model.TeamsEntity

@Database(
    version = 1,
    entities = [TeamsEntity::class],
    exportSchema = true
)
abstract class MyRoomDatabase : RoomDatabase() {

    abstract fun teamDao(): TeamDao
}