package com.wahyu.core.data.source.remote.response.matchdetail

data class Fixture(
    val awayTeam: AwayTeam,
    val elapsed: Int,
    val event_date: String,
    val event_timestamp: Int,
    val events: List<Event>,
    val firstHalfStart: Int,
    val fixture_id: Int,
    val goalsAwayTeam: Int,
    val goalsHomeTeam: Int,
    val homeTeam: HomeTeam,
    val league: League,
    val league_id: Int,
    val lineups: Lineups,
    val players: List<Player>,
    val referee: String,
    val round: String,
    val score: Score,
    val secondHalfStart: Int,
    val statistics: Statistics,
    val status: String,
    val statusShort: String,
    val venue: String
)