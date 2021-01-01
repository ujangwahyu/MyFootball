package com.wahyu.core.domain.usecase

import com.wahyu.core.data.source.Resource
import com.wahyu.core.data.source.remote.response.match.Match
import com.wahyu.core.data.source.remote.response.standing.Standing
import com.wahyu.core.data.source.remote.response.team.Team
import com.wahyu.core.data.source.remote.response.topscore.Topscorer
import com.wahyu.core.data.source.remote.response.upcoming.UpcomingMatch
import com.wahyu.core.data.source.remote.response.todaymatch.TodayMatch
import com.wahyu.core.domain.repository.IFootballRepository
import kotlinx.coroutines.flow.Flow

class FootballInteractor (private val repository: IFootballRepository) : FootballUsecase {

    override fun getStanding(id: Int): Flow<Resource<out List<List<Standing>>>> {
        return repository.getStanding(id)
    }

    override fun getTeam(id: Int): Flow<Resource<out List<Team>>> {
        return repository.getTeam(id)
    }

    override fun getLastMatch(id: Int): Flow<Resource<out List<Match>>> {
        return repository.getLastMatch(id)
    }

    override fun getUpcomingMatch(id: Int): Flow<Resource<out List<UpcomingMatch>>> {
        return repository.getUpcomingMatch(id)
    }

    override fun getTopScores(id: Int): Flow<Resource<out List<Topscorer>>> {
        return repository.getTopScores(id)
    }

    override fun getTodayMatch(date: String): Flow<Resource<out List<TodayMatch>>> {
        return repository.getTodayMatch(date)
    }

    override fun getMatchByLeague(leagueId: Int, date: String): Flow<Resource<out List<Match>>> {
        return repository.getMatchByLeague(leagueId, date)
    }

}