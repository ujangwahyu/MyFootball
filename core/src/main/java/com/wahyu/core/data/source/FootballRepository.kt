package com.wahyu.core.data.source

import com.wahyu.core.data.source.local.LocalDataSource
import com.wahyu.core.data.source.remote.RemoteDataSource
import com.wahyu.core.data.source.remote.response.leagues.League
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
    override fun getStanding(id: Int): Flow<Result<List<List<Standing>>>> {
        return remoteDataSource.getStanding(id)
    }

    override fun getTeam(id: Int): Flow<Result<List<Team>>> {
        return remoteDataSource.getTeam(id)
    }

    override fun getLeagueByCountry(country: String, season: String): Flow<Result<List<League>>> {
        return remoteDataSource.getLeagueByCountry(country, season)
    }

    override fun getMatchByLeague(idLeague: Int, date: String): Flow<Result<List<Match>>> {
        return remoteDataSource.getMatchByLeague(idLeague, date)
    }
}