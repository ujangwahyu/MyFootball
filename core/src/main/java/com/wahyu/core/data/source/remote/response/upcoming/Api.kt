package com.wahyu.core.data.source.remote.response.upcoming

data class Api(
    val fixtures: List<UpcomingMatch>,
    val results: Int
)