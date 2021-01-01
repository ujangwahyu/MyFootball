package com.wahyu.core.data.source.remote.response.todaymatch

data class Api(
    val fixtures: List<TodayMatch>,
    val results: Int
)