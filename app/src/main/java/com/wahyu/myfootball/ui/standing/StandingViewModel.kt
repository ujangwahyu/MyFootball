package com.wahyu.myfootball.ui.standing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.wahyu.core.domain.usecase.FootballUsecase

class StandingViewModel (footballUsecase: FootballUsecase) : ViewModel() {
    val standing = footballUsecase.getStanding(524).asLiveData()
}