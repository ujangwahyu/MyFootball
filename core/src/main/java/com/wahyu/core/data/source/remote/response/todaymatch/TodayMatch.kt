package com.wahyu.core.data.source.remote.response.todaymatch

data class TodayMatch(
    val awayTeam: AwayTeam,
    val elapsed: Int,
    val event_date: String,
    val event_timestamp: Int,
    val firstHalfStart: Any,
    val fixture_id: Int,
    val goalsAwayTeam: Int,
    val goalsHomeTeam: Int,
    val homeTeam: HomeTeam,
    val league: League,
    val league_id: Int,
    val referee: Any,
    val round: String,
    val score: Score,
    val secondHalfStart: Int,
    val status: String,
    val statusShort: String,
    val venue: String
)