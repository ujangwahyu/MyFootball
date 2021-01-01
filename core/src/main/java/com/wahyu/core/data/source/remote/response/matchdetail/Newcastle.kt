package com.wahyu.core.data.source.remote.response.matchdetail

data class Newcastle(
    val coach: String,
    val coach_id: Int,
    val formation: String,
    val startXI: List<StartXI>,
    val substitutes: List<Substitute>
)