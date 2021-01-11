package com.wahyu.core.domain.usecase

import com.wahyu.core.data.source.Result
import com.wahyu.core.data.source.remote.response.leagues.League
import com.wahyu.core.data.source.remote.response.match.Match
import com.wahyu.core.data.source.remote.response.standing.Standing
import com.wahyu.core.data.source.remote.response.team.Team
import com.wahyu.core.domain.repository.IFootballRepository
import kotlinx.coroutines.flow.Flow

class FootballInteractor (private val repository: IFootballRepository) : FootballUsecase {

    override fun getStanding(id: Int): Flow<Result<List<List<Standing>>>> {
        return repository.getStanding(id)
    }

    override fun getTeam(id: Int): Flow<Result<List<Team>>> {
        return repository.getTeam(id)
    }

    override fun getLeagueByCountry(country: String, season: String): Flow<Result<List<League>>> {
        return repository.getLeagueByCountry(country, season)
    }

    override fun getMatchByLeague(leagueId: Int, date: String): Flow<Result<List<Match>>> {
        return repository.getMatchByLeague(leagueId, date)
    }

}