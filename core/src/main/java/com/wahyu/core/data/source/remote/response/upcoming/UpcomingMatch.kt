package com.wahyu.core.data.source.remote.response.upcoming

data class UpcomingMatch(
    val awayTeam: AwayTeam,
    val elapsed: Int,
    val event_date: String,
    val event_timestamp: Int,
    val firstHalfStart: Int,
    val fixture_id: Int,
    val goalsAwayTeam: Int,
    val goalsHomeTeam: Int,
    val homeTeam: HomeTeam,
    val league: League,
    val league_id: Int,
    val referee: String,
    val round: String,
    val score: Score,
    val secondHalfStart: Int,
    val status: String,
    val statusShort: String,
    val venue: String
)