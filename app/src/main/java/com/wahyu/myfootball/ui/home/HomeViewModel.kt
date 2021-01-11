package com.wahyu.myfootball.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.wahyu.core.data.source.Result
import com.wahyu.core.data.source.remote.response.leagues.League
import com.wahyu.core.data.source.remote.response.match.Match
import com.wahyu.core.domain.usecase.FootballUsecase

class HomeViewModel (val footballUsecase: FootballUsecase) : ViewModel() {

    fun getLeague(country: String, season: String) : LiveData<Result<List<League>>> {
        return footballUsecase.getLeagueByCountry(country, season).asLiveData()
    }

    fun getMatchByLeague(id: Int, date: String): LiveData<Result<List<Match>>> {
        return footballUsecase.getMatchByLeague(id, date).asLiveData()
    }
}