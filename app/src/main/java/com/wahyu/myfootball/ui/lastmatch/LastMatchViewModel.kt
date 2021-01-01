package com.wahyu.myfootball.ui.lastmatch

import androidx.lifecycle.ViewModel
import com.wahyu.core.domain.usecase.FootballUsecase

class LastMatchViewModel (footballUsecase: FootballUsecase) : ViewModel() {
    val lastMatch = footballUsecase.getLastMatch(524)
}