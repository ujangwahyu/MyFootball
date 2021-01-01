package com.wahyu.core.data.source.remote.response.standing

data class Standing(
    val all: All,
    val away: Away,
    val description: String,
    val forme: String,
    val goalsDiff: Int,
    val group: String,
    val home: Home,
    val lastUpdate: String,
    val logo: String,
    val points: Int,
    val rank: Int,
    val status: String,
    val teamName: String,
    val team_id: Int
)