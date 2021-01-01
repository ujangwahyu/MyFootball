package com.wahyu.core.data.source.remote.response.leagues

data class League(
    val country: String,
    val country_code: String,
    val coverage: Coverage,
    val flag: String,
    val is_current: Int,
    val league_id: Int,
    val logo: String,
    val name: String,
    val season: Int,
    val season_end: String,
    val season_start: String,
    val standings: Int,
    val type: String
)