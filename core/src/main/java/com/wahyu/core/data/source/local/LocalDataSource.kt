package com.wahyu.core.data.source.local

import com.wahyu.core.data.source.local.database.FootballDao
import com.wahyu.core.data.source.local.entity.TeamEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val footballDao: FootballDao) {

    fun getFavoriteTeam() : Flow<List<TeamEntity>> = footballDao.getFavoriteTeam()

    suspend fun insertFavoriteTeam(restaurantList: List<TeamEntity>) = footballDao.insertFavoriteTeam(restaurantList)

    fun setFavoriteTeam(restaurantList: TeamEntity, newState: Boolean) {
        restaurantList.isFavorite = newState
        footballDao.updateFavoriteTeam(restaurantList)
    }

}