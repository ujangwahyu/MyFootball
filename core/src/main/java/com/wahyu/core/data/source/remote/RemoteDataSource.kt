package com.wahyu.core.data.source.remote

import android.util.Log
import com.wahyu.core.data.network.ApiService
import com.wahyu.core.data.source.Result
import com.wahyu.core.data.source.remote.response.leagues.League
import com.wahyu.core.data.source.remote.response.match.Match
import com.wahyu.core.data.source.remote.response.standing.Standing
import com.wahyu.core.data.source.remote.response.team.Team
import com.wahyu.core.data.source.remote.response.topscore.Topscorer
import com.wahyu.core.data.source.remote.response.upcoming.UpcomingMatch
import com.wahyu.core.data.source.remote.response.todaymatch.TodayMatch
import com.wahyu.core.utils.Constants.API_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource (private val apiService: ApiService) {

    fun getStanding(id: Int): Flow<Result<out List<List<Standing>>>> {
        return flow {
            try {
                val response = apiService.getStanding(API_KEY, id)
                val data = response.api.standings
                if (data.isNotEmpty()){
                    emit(Result.Success(response.api.standings))
                } else {
                    emit(Result.Empty)
                }
            }catch (e: Exception) {
                emit(Result.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getTeam(id: Int): Flow<Result<out List<Team>>> {
        return flow {
            try {
                val response = apiService.getTeam(API_KEY,id)
                val data = response.api.teams
                if (data.isNotEmpty()){
                    emit(Result.Success(response.api.teams))
                } else {
                    emit(Result.Empty)
                }
            } catch (e: Exception) {
                emit(Result.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getTopscore(id: Int): Flow<Result<out List<Topscorer>>> {
        return flow {
            try {
                val response = apiService.getTopScorer(API_KEY, id)
                val data = response.api.topscorers
                if (data.isNotEmpty()){
                    emit(Result.Success(response.api.topscorers))
                } else {
                    emit(Result.Empty)
                }
            } catch (e: Exception) {
                emit(Result.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getLeagueByCountry(country: String, season: String) : Flow<Result<out List<League>>> {
        return flow {
            try {
                val response = apiService.getLeagueByCountry(API_KEY, country, season)
                val data = response.api.leagues
                if (data.isNotEmpty()) {
                    emit(Result.Success(response.api.leagues))
                } else {
                    emit(Result.Empty)
                }
            }catch (e: Exception) {
                emit(Result.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getMatchByLeague(idLeague: Int, date: String): Flow<Result<out List<Match>>> {
        return flow {
            try {
                val response = apiService.getMatchByLeague(API_KEY, idLeague, date)
                val data = response.api.fixtures
                if (data.isNotEmpty()){
                    emit(Result.Success(response.api.fixtures))
                } else {
                    emit(Result.Empty)
                }
            } catch (e: Exception) {
                emit(Result.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}