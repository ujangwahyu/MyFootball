package com.wahyu.core.data.source.remote.response.topscore

data class Topscorer(
    val cards: Cards,
    val firstname: String,
    val games: Games,
    val goals: Goals,
    val lastname: String,
    val nationality: String,
    val penalty: Penalty,
    val player_id: Int,
    val player_name: String,
    val position: String,
    val shots: Shots,
    val team_id: Int,
    val team_name: String
)