package com.glamera.football.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.glamera.football.data.database.model.TeamsEntity

@Dao
interface TeamDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(teamsEntity: TeamsEntity)

    @Query("SELECT * FROM team WHERE name = :name")
    fun getTeams(name: String):TeamsEntity
}