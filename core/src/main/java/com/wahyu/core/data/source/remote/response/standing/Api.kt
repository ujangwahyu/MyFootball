package com.wahyu.core.data.source.remote.response.standing

data class Api(
    val results: Int,
    val standings: List<List<Standing>>
)