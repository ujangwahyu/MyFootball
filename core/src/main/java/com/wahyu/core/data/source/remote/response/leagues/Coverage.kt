package com.wahyu.core.data.source.remote.response.leagues

data class Coverage(
    val fixtures: Fixtures,
    val odds: Boolean,
    val players: Boolean,
    val predictions: Boolean,
    val standings: Boolean,
    val topScorers: Boolean
)