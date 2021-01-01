package com.wahyu.core.data.source.remote.response.leagues

data class Fixtures(
    val events: Boolean,
    val lineups: Boolean,
    val players_statistics: Boolean,
    val statistics: Boolean
)