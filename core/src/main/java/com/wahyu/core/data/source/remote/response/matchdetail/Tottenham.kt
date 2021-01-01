package com.wahyu.core.data.source.remote.response.matchdetail

data class Tottenham(
    val coach: String,
    val coach_id: Int,
    val formation: String,
    val startXI: List<StartXIX>,
    val substitutes: List<SubstituteX>
)