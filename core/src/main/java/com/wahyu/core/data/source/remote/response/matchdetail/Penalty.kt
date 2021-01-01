package com.wahyu.core.data.source.remote.response.matchdetail

data class Penalty(
    val commited: Int,
    val missed: Int,
    val saved: Int,
    val success: Int,
    val won: Int
)