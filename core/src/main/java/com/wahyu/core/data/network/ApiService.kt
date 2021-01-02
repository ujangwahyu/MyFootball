package com.wahyu.core.data.network

import com.wahyu.core.data.source.remote.response.leagues.LeagueResponse
import com.wahyu.core.data.source.remote.response.match.MatchResponse
import com.wahyu.core.data.source.remote.response.standing.StandingResponse
import com.wahyu.core.data.source.remote.response.team.TeamResponse
import com.wahyu.core.data.source.remote.response.todaymatch.TodayMatchResponse
import com.wahyu.core.data.source.remote.response.topscore.TopScoreResponse
import com.wahyu.core.data.source.remote.response.upcoming.UpcomingMatchResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {

    @GET("v2/leagueTable/{id}")
    suspend fun getStanding(
        @Header("x-rapidapi-key") apiKey: String,
        @Path("id") id: Int
    ): StandingResponse

    @GET("v2/teams/league/{id}")
    suspend fun getTeam(
        @Header("x-rapidapi-key") apiKey: String,
        @Path("id") id: Int
    ): TeamResponse

    @GET("v2/fixtures/league/{id}/last/20")
    suspend fun getLastMatch(
        @Header("x-rapidapi-key") apiKey: String,
        @Path("id") id: Int
    ): MatchResponse

    @GET("v2/fixtures/league/{id}/next/20")
    suspend fun getUpcomingMatch(
        @Header("x-rapidapi-key") apiKey: String,
        @Path("id") id: Int
    ): UpcomingMatchResponse

    @GET("v2/topscorers/{id}")
    suspend fun getTopScorer(
        @Header("x-rapidapi-key") apiKey: String,
        @Path("id") id: Int
    ): TopScoreResponse

    @GET("v2/fixtures/date/{date}")
    suspend fun getTodayMatch(
        @Header("x-rapidapi-key") apiKey: String,
        @Path("date") date: String
    ): TodayMatchResponse

    @GET("v2/leagues/country/{country_name}/{season}")
    suspend fun getLeagueByCountry(
        @Header("x-rapidapi-key") apiKey: String,
        @Path("country_name") countryName: String,
        @Path("season") season: String
    ): LeagueResponse

    @GET("v2/fixtures/league/{league_id}/{date}/last/50")
    suspend fun getMatchByLeague(
        @Header("x-rapidapi-key") apiKey: String,
        @Path("league_id") leagueId: Int,
        @Path("date") date: String
    ): MatchResponse

    @GET("v2/fixtures/id/{fixture_id}")
    suspend fun getMatchDetail(
        @Header("x-rapidapi-key") apiKey: String,
        @Path("fixture_id") fixtureId: String
    ): MatchResponse

}