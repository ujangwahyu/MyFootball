package com.wahyu.core.domain.model.topscore

data class Penalty(
    val commited: Any,
    val missed: Int,
    val saved: Any,
    val success: Int,
    val won: Int
)