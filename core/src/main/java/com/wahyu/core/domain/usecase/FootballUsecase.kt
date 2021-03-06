package com.wahyu.core.domain.usecase

import com.wahyu.core.data.source.Result
import com.wahyu.core.data.source.remote.response.leagues.League
import com.wahyu.core.data.source.remote.response.match.Match
import com.wahyu.core.data.source.remote.response.standing.Standing
import com.wahyu.core.data.source.remote.response.team.Team
import kotlinx.coroutines.flow.Flow

interface FootballUsecase {
    fun getStanding(id: Int): Flow<Result<List<List<Standing>>>>

    fun getTeam(id: Int): Flow<Result<List<Team>>>

    fun getLeagueByCountry(country: String, season: String): Flow<Result<List<League>>>

    fun getMatchByLeague(leagueId: Int, date: String): Flow<Result<List<Match>>>
}