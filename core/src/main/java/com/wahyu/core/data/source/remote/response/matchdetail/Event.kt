package com.wahyu.core.data.source.remote.response.matchdetail

data class Event(
    val assist: String,
    val assist_id: Int,
    val comments: Any,
    val detail: String,
    val elapsed: Int,
    val elapsed_plus: Any,
    val player: String,
    val player_id: Int,
    val teamName: String,
    val team_id: Int,
    val type: String
)