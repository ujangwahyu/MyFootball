package com.wahyu.core.data.source.remote.response.topscore

data class Penalty(
    val commited: Any,
    val missed: Int,
    val saved: Any,
    val success: Int,
    val won: Int
)