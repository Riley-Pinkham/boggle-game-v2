package com.bogglegame

/**
 * Represents the different game modes available in Boggle.
 */
enum class GameMode(val size: Int, val displayName: String) {
    CLASSIC(4, "Classic Boggle (4x4)"),
    BIG_BOGGLE(5, "Big Boggle (5x5)"),
    SUPER_BIG_BOGGLE(6, "Super Big Boggle (6x6)")
}
