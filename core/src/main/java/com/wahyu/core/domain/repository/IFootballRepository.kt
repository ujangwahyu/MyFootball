package com.wahyu.core.domain.repository

import com.wahyu.core.data.source.Resource
import com.wahyu.core.data.source.remote.response.match.Match
import com.wahyu.core.data.source.remote.response.standing.Standing
import com.wahyu.core.data.source.remote.response.team.Team
import com.wahyu.core.data.source.remote.response.topscore.Topscorer
import com.wahyu.core.data.source.remote.response.upcoming.UpcomingMatch
import com.wahyu.core.data.source.remote.response.todaymatch.TodayMatch
import kotlinx.coroutines.flow.Flow

interface IFootballRepository {
    fun getStanding(id: Int): Flow<Resource<out List<List<Standing>>>>

    fun getTeam(id: Int): Flow<Resource<out List<Team>>>

    fun getLastMatch(id: Int): Flow<Resource<out List<Match>>>

    fun getUpcomingMatch(id: Int): Flow<Resource<out List<UpcomingMatch>>>

    fun getTopScores(id: Int): Flow<Resource<out List<Topscorer>>>

    fun getTodayMatch(date: String): Flow<Resource<out List<TodayMatch>>>

    fun getMatchByLeague(idLeague: Int, date: String): Flow<Resource<out List<Match>>>
}