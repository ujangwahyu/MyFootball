package com.wahyu.core.domain.model.upcoming

data class Api(
    val fixtures: List<UpcomingMatch>,
    val results: Int
)