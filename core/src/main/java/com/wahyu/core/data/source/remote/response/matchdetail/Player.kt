package com.wahyu.core.data.source.remote.response.matchdetail

data class Player(
    val captain: String,
    val cards: Cards,
    val dribbles: Dribbles,
    val duels: Duels,
    val event_id: Int,
    val fouls: Fouls,
    val goals: Goals,
    val minutes_played: Int,
    val number: Int,
    val offsides: Any,
    val passes: Passes,
    val penalty: Penalty,
    val player_id: Int,
    val player_name: String,
    val position: String,
    val rating: String,
    val shots: Shots,
    val substitute: String,
    val tackles: Tackles,
    val team_id: Int,
    val team_name: String,
    val updateAt: Int
)