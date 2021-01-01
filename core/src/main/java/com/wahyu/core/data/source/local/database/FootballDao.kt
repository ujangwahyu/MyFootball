package com.wahyu.core.data.source.local.database

import androidx.room.*
import com.wahyu.core.data.source.local.entity.TeamEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FootballDao {
    @Query("SELECT * FROM team")
    fun getAllTeam(): Flow<List<TeamEntity>>

    @Query("SELECT * FROM team where isFavorite = 1")
    fun getFavoriteTeam(): Flow<List<TeamEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteTeam(team: List<TeamEntity>)

    @Update
    fun updateFavoriteTeam(team: TeamEntity)

}