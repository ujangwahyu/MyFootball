package com.wahyu.core.data.source.remote.response.topscore

data class Api(
    val results: Int,
    val topscorers: List<Topscorer>
)