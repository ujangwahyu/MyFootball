package com.wahyu.core.data.source.remote.response.matchdetail

data class Statistics(
    val BallPossession: BallPossession,
    val BlockedShots: BlockedShots,
    val CornerKicks: CornerKicks,
    val Fouls: FoulsX,
    val GoalkeeperSaves: GoalkeeperSaves,
    val Offsides: Offsides,
    val Passes: PassesX,
    val Passesaccurate: PassesAccurate,
    val RedCards: RedCards,
    val Shotsinsidebox: ShotsInsidebox,
    val ShotsoffGoal: ShotsOffGoal,
    val ShotsonGoal: ShotsOnGoal,
    val Shotsoutsidebox: ShotsOutsidebox,
    val TotalShots: TotalShots,
    val Totalpasses: TotalPasses,
    val YellowCards: YellowCards
)