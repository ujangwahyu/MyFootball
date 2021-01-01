package com.wahyu.myfootball.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.wahyu.core.domain.usecase.FootballUsecase
import com.wahyu.myfootball.utils.getLastDate

class HomeViewModel (footballUsecase: FootballUsecase) : ViewModel() {
    val team = footballUsecase.getTeam(524).asLiveData()
    val todayMatch = footballUsecase.getTodayMatch(getLastDate()).asLiveData()
    val lastMatch = footballUsecase.getLastMatch(524).asLiveData()
    val upcomingMatch = footballUsecase.getUpcomingMatch(524).asLiveData()
}