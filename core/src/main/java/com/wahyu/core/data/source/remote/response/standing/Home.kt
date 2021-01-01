package com.wahyu.core.data.source.remote.response.standing

data class Home(
    val draw: Int,
    val goalsAgainst: Int,
    val goalsFor: Int,
    val lose: Int,
    val matchsPlayed: Int,
    val win: Int
)