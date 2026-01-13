package com.boggle.domain.model.variant

/**
 * Represents the three official Boggle game variants.
 *
 * Boggle is a word game designed by Allan Turoff and originally distributed by Parker Brothers (now Hasbro).
 * The game has evolved into three main variants, each with different board sizes and gameplay characteristics.
 *
 * ## Variants:
 * - **Standard Boggle (4x4)**: The original game with a 4x4 grid of 16 dice, 3-minute timer
 * - **Big Boggle (5x5)**: Introduced in 1979, featuring a 5x5 grid of 25 dice, 3-minute timer
 * - **Super Big Boggle (6x6)**: The largest variant with a 6x6 grid of 36 dice, 4-minute timer
 *
 * This sealed interface ensures type-safety when selecting game variants and provides
 * common properties that all variants must define.
 *
 * @property displayName The human-readable name of the variant
 * @property gridSize The dimension of the square grid (4, 5, or 6)
 * @property diceCount The total number of dice on the board (calculated as gridSize²)
 *
 * @see <a href="https://en.wikipedia.org/wiki/Boggle">Boggle on Wikipedia</a>
 */
sealed interface GameVariant {
    val displayName: String
    val gridSize: Int
    val diceCount: Int get() = gridSize * gridSize

    /**
     * Standard Boggle (4x4)
     *
     * The classic Boggle game with:
     * - 4x4 grid (16 dice)
     * - 3-minute timer
     * - Minimum word length: 3 letters
     * - Uses the "New Boggle" dice configuration (post-2008)
     *
     * Scoring:
     * - 3-4 letter words: 1 point
     * - 5 letter words: 2 points
     * - 6 letter words: 3 points
     * - 7 letter words: 5 points
     * - 8+ letter words: 11 points
     */
    data object Boggle4x4 : GameVariant {
        override val displayName: String = "Boggle"
        override val gridSize: Int = 4
    }

    /**
     * Big Boggle (5x5)
     *
     * An expanded version of Boggle with:
     * - 5x5 grid (25 dice)
     * - 3-minute timer
     * - Minimum word length: 4 letters
     * - Optional digraph die (with letter combinations: An, Er, He, In, Qu, Th)
     *
     * Scoring:
     * - 4 letter words: 1 point
     * - 5 letter words: 2 points
     * - 6 letter words: 3 points
     * - 7 letter words: 5 points
     * - 8+ letter words: 11 points
     *
     * Note: The digraph die is a player-selectable option that replaces one standard die.
     */
    data object BigBoggle5x5 : GameVariant {
        override val displayName: String = "Big Boggle"
        override val gridSize: Int = 5
    }

    /**
     * Super Big Boggle (6x6)
     *
     * The largest Boggle variant with:
     * - 6x6 grid (36 dice)
     * - 4-minute timer
     * - Minimum word length: 4 letters
     * - Includes one digraph die (with An, Er, He, In, Qu, Th)
     * - Includes one blank die (3 blank faces that cannot be traversed)
     *
     * Scoring:
     * - 4 letter words: 1 point
     * - 5 letter words: 2 points
     * - 6 letter words: 3 points
     * - 7 letter words: 5 points
     * - 8 letter words: 11 points
     * - 9+ letter words: 2 points per letter
     *
     * Special features:
     * - Blank spaces can be navigated around but not through
     * - Digraph combinations must be used in sequence (e.g., "Th" cannot be reversed)
     */
    data object SuperBigBoggle6x6 : GameVariant {
        override val displayName: String = "Super Big Boggle"
        override val gridSize: Int = 6
    }
}
