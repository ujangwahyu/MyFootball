package com.wahyu.match.di

import com.wahyu.match.ui.MatchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object MatchModule {
    val viewModelModule = module {
        viewModel { MatchViewModel(get()) }
    }
}