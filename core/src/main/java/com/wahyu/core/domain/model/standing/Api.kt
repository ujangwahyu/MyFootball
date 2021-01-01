package com.wahyu.core.domain.model.standing

data class Api(
    val results: Int,
    val standings: List<List<Standing>>
)