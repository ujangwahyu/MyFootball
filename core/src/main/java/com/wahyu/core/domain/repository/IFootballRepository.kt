package com.wahyu.core.domain.repository

import com.wahyu.core.data.source.Result
import com.wahyu.core.data.source.remote.response.leagues.League
import com.wahyu.core.data.source.remote.response.match.Match
import com.wahyu.core.data.source.remote.response.standing.Standing
import com.wahyu.core.data.source.remote.response.team.Team
import kotlinx.coroutines.flow.Flow

interface IFootballRepository {
    fun getStanding(id: Int): Flow<Result<out List<List<Standing>>>>

    fun getTeam(id: Int): Flow<Result<out List<Team>>>

    fun getLeagueByCountry(country: String, season: String): Flow<Result<out List<League>>>

    fun getMatchByLeague(idLeague: Int, date: String): Flow<Result<out List<Match>>>
}