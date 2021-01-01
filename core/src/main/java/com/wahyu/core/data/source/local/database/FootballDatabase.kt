package com.wahyu.core.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wahyu.core.data.source.local.entity.TeamEntity

@Database(entities = [TeamEntity::class], version = 1, exportSchema = false)
abstract class FootballDatabase : RoomDatabase() {
    abstract fun footballDao(): FootballDao
}