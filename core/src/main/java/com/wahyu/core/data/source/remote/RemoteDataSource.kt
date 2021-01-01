package com.wahyu.core.data.source.remote

import android.util.Log
import com.wahyu.core.data.network.ApiService
import com.wahyu.core.data.source.Resource
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

    fun getStanding(id: Int): Flow<Resource<out List<List<Standing>>>> {
        return flow {
            try {
                val response = apiService.getStanding(API_KEY, id)
                val data = response.api.standings
                if (data.isNotEmpty()){
                    emit(Resource.Success(response.api.standings))
                } else {
                    emit(Resource.Empty)
                }
            }catch (e: Exception) {
                emit(Resource.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getTeam(id: Int): Flow<Resource<out List<Team>>> {
        return flow {
            try {
                val response = apiService.getTeam(API_KEY,id)
                val data = response.api.teams
                if (data.isNotEmpty()){
                    emit(Resource.Success(response.api.teams))
                } else {
                    emit(Resource.Empty)
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getLastMatch(id: Int): Flow<Resource<out List<Match>>> {
        return flow {
            try {
                val response = apiService.getLastMatch(API_KEY,id)
                val data = response.api.fixtures
                if (data.isNotEmpty()){
                    emit(Resource.Success(response.api.fixtures))
                } else {
                    emit(Resource.Empty)
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getUpcoming(id: Int): Flow<Resource<out List<UpcomingMatch>>> {
        return flow {
            try {
                val response = apiService.getUpcomingMatch(API_KEY,id)
                val data = response.api.fixtures
                if (data.isNotEmpty()){
                    emit(Resource.Success(response.api.fixtures))
                } else {
                    emit(Resource.Empty)
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getTopscore(id: Int): Flow<Resource<out List<Topscorer>>> {
        return flow {
            try {
                val response = apiService.getTopScorer(API_KEY, id)
                val data = response.api.topscorers
                if (data.isNotEmpty()){
                    emit(Resource.Success(response.api.topscorers))
                } else {
                    emit(Resource.Empty)
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getTodayMatch(date: String): Flow<Resource<out List<TodayMatch>>> {
        return flow {
            try {
                val response = apiService.getTodayMatch(API_KEY, date)
                val data = response.api.fixtures
                if (data.isNotEmpty()){
                    emit(Resource.Success(response.api.fixtures))
                } else {
                    emit(Resource.Empty)
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getMatchByLeague(idLeague: Int, date: String): Flow<Resource<out List<Match>>> {
        return flow {
            try {
                val response = apiService.getMatchByLeague(API_KEY, idLeague, date)
                val data = response.api.fixtures
                if (data.isNotEmpty()){
                    emit(Resource.Success(response.api.fixtures))
                } else {
                    emit(Resource.Empty)
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }


}