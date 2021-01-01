package com.wahyu.myfootball.di

import com.wahyu.core.domain.usecase.FootballInteractor
import com.wahyu.core.domain.usecase.FootballUsecase
import com.wahyu.myfootball.ui.home.HomeViewModel
import com.wahyu.myfootball.ui.lastmatch.LastMatchViewModel
import com.wahyu.myfootball.ui.standing.StandingViewModel
import com.wahyu.myfootball.ui.topscore.TopScoreViewModel
import com.wahyu.myfootball.ui.upcoming.UpcomingViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {
    val useCaseModule = module {
        factory<FootballUsecase> { FootballInteractor(get()) }
    }

    val viewModelModule = module {
        viewModel { HomeViewModel(get()) }
        viewModel { LastMatchViewModel(get()) }
        viewModel { StandingViewModel(get()) }
        viewModel { TopScoreViewModel(get()) }
        viewModel { UpcomingViewModel(get()) }
    }
}