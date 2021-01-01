package com.wahyu.core.data.source

import com.wahyu.core.data.source.local.LocalDataSource
import com.wahyu.core.data.source.remote.RemoteDataSource
import com.wahyu.core.data.source.remote.response.match.Match
import com.wahyu.core.data.source.remote.response.standing.Standing
import com.wahyu.core.data.source.remote.response.team.Team
import com.wahyu.core.data.source.remote.response.topscore.Topscorer
import com.wahyu.core.data.source.remote.response.upcoming.UpcomingMatch
import com.wahyu.core.data.source.remote.response.todaymatch.TodayMatch
import com.wahyu.core.domain.repository.IFootballRepository
import com.wahyu.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow

class FootballRepository (
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors

): IFootballRepository {
    override fun getStanding(id: Int): Flow<Resource<out List<List<Standing>>>> {
        return remoteDataSource.getStanding(id)
    }

    override fun getTeam(id: Int): Flow<Resource<out List<Team>>> {
        return remoteDataSource.getTeam(id)
    }

    override fun getLastMatch(id: Int): Flow<Resource<out List<Match>>> {
        return remoteDataSource.getLastMatch(id)
    }

    override fun getUpcomingMatch(id: Int): Flow<Resource<out List<UpcomingMatch>>> {
        return remoteDataSource.getUpcoming(id)
    }

    override fun getTopScores(id: Int): Flow<Resource<out List<Topscorer>>> {
        return remoteDataSource.getTopscore(id)
    }

    override fun getTodayMatch(date: String): Flow<Resource<out List<TodayMatch>>> {
        return remoteDataSource.getTodayMatch(date)
    }

    override fun getMatchByLeague(idLeague: Int, date: String): Flow<Resource<out List<Match>>> {
        return remoteDataSource.getMatchByLeague(idLeague, date)
    }
}