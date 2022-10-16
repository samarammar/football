package com.glamera.football.data.database.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "team")
class TeamsEntity (
    @PrimaryKey
    @NonNull
    val name:String,val address:String,val clubColors:String,val crest:String
)
