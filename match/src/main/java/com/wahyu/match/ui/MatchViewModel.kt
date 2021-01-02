package com.wahyu.match.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.wahyu.core.data.source.Result
import com.wahyu.core.data.source.remote.response.match.Match
import com.wahyu.core.domain.usecase.FootballUsecase

class MatchViewModel(val footballUsecase: FootballUsecase ) : ViewModel() {

    fun getMatchByLeague(id: Int, date: String): LiveData<Result<out List<Match>>> {
        return footballUsecase.getMatchByLeague(id, date).asLiveData()
    }
}

