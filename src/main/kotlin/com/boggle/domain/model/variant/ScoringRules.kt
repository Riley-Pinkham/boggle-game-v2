package com.boggle.domain.model.variant

/**
 * Represents a scoring tier for a specific word length range in Boggle.
 *
 * Each tier defines the minimum word length required and the points awarded for words
 * in that tier. The tier applies to all words from [minLength] up to (but not including)
 * the next tier's minimum length, or indefinitely if it's the last tier.
 *
 * ## Example:
 * A tier with `minLength = 5` and `points = 2` awards 2 points to 5-letter words,
 * and continues to award 2 points for 6-letter, 7-letter words, etc., until a higher
 * tier is encountered.
 *
 * @property minLength Minimum word length for this tier (inclusive). Must be at least 3.
 * @property points Points awarded for words in this tier. Must be non-negative.
 */
data class ScoringTier(
    val minLength: Int,
    val points: Int
) {
    init {
        require(minLength >= 3) { 
            "Minimum word length must be at least 3 letters (Boggle minimum), got $minLength" 
        }
        require(points >= 0) { 
            "Points must be non-negative, got $points" 
        }
    }
}

/**
 * Defines scoring rules for a Boggle game variant.
 *
 * Scoring in Boggle rewards longer words with progressively more points. Each variant
 * has its own scoring structure, with points increasing as word length increases.
 *
 * ## Standard Scoring Pattern:
 * Boggle variants typically use a tier-based system where point values jump at specific
 * word lengths (e.g., 3-4 letters = 1pt, 5 letters = 2pts, etc.). The highest tier
 * applies to all words of that length or greater.
 *
 * ## Special Cases:
 * - **Super Big Boggle (6x6)**: Words with 9 or more letters use a formula-based scoring
 *   system (2 points per letter) instead of a fixed tier value.
 *
 * @property tiers List of scoring tiers, must be sorted by minLength in ascending order
 * @property usePerLetterScoring When true, words with 9+ letters score 2 points per letter
 *                                (used only in Super Big Boggle 6x6 variant)
 *
 * @see ScoringTier
 */
data class ScoringRules(
    val tiers: List<ScoringTier>,
    val usePerLetterScoring: Boolean = false
) {
    init {
        require(tiers.isNotEmpty()) { 
            "Scoring rules must have at least one tier" 
        }
        
        // Validate tiers are sorted by minLength in ascending order
        tiers.zipWithNext().forEach { (current, next) ->
            require(current.minLength < next.minLength) {
                "Scoring tiers must be sorted by minLength in ascending order. " +
                "Found tier with minLength=${current.minLength} before tier with minLength=${next.minLength}"
            }
        }
    }
    
    /**
     * Calculate points awarded for a word of the given length.
     *
     * The calculation follows these rules:
     * 1. If the word is shorter than the minimum length (first tier), return 0 points
     * 2. If [usePerLetterScoring] is true and word length ≥ 9, return wordLength × 2
     * 3. Otherwise, find the highest tier where minLength ≤ wordLength and return its points
     *
     * ## Examples (4x4 Boggle):
     * ```
     * calculatePoints(2) // Returns 0 (below 3-letter minimum)
     * calculatePoints(3) // Returns 1 (3-4 letter tier)
     * calculatePoints(4) // Returns 1 (3-4 letter tier)
     * calculatePoints(5) // Returns 2 (5 letter tier)
     * calculatePoints(8) // Returns 11 (8+ letter tier)
     * ```
     *
     * ## Examples (6x6 Super Big Boggle with per-letter scoring):
     * ```
     * calculatePoints(8)  // Returns 11 (fixed tier)
     * calculatePoints(9)  // Returns 18 (9 × 2 formula)
     * calculatePoints(12) // Returns 24 (12 × 2 formula)
     * ```
     *
     * @param wordLength The length of the word to score
     * @return Points earned for the word, or 0 if below minimum length
     */
    fun calculatePoints(wordLength: Int): Int {
        // Words below the minimum length score 0 points
        if (wordLength < tiers.first().minLength) return 0
        
        // Special case: Super Big Boggle 6x6 formula for 9+ letter words
        if (usePerLetterScoring && wordLength >= 9) {
            return wordLength * 2
        }
        
        // Find the highest tier that applies to this word length
        return tiers.lastOrNull { it.minLength <= wordLength }?.points ?: 0
    }
    
    companion object {
        /**
         * Standard Boggle (4x4) scoring rules.
         *
         * Minimum word length: 3 letters
         *
         * Scoring breakdown:
         * - 3-4 letter words: 1 point
         * - 5 letter words: 2 points
         * - 6 letter words: 3 points
         * - 7 letter words: 5 points
         * - 8+ letter words: 11 points
         *
         * This scoring structure encourages players to find longer words while still
         * rewarding shorter 3-4 letter words that are easier to spot.
         */
        fun forBoggle4x4(): ScoringRules = ScoringRules(
            tiers = listOf(
                ScoringTier(minLength = 3, points = 1),
                ScoringTier(minLength = 5, points = 2),
                ScoringTier(minLength = 6, points = 3),
                ScoringTier(minLength = 7, points = 5),
                ScoringTier(minLength = 8, points = 11)
            )
        )
        
        /**
         * Big Boggle (5x5) scoring rules.
         *
         * Minimum word length: 4 letters
         *
         * Scoring breakdown:
         * - 4 letter words: 1 point
         * - 5 letter words: 2 points
         * - 6 letter words: 3 points
         * - 7 letter words: 5 points
         * - 8+ letter words: 11 points
         *
         * The increased board size (5x5) allows for longer words, so the minimum
         * word length is raised to 4 letters compared to standard Boggle.
         */
        fun forBigBoggle5x5(): ScoringRules = ScoringRules(
            tiers = listOf(
                ScoringTier(minLength = 4, points = 1),
                ScoringTier(minLength = 5, points = 2),
                ScoringTier(minLength = 6, points = 3),
                ScoringTier(minLength = 7, points = 5),
                ScoringTier(minLength = 8, points = 11)
            )
        )
        
        /**
         * Super Big Boggle (6x6) scoring rules.
         *
         * Minimum word length: 4 letters
         *
         * Scoring breakdown:
         * - 4 letter words: 1 point
         * - 5 letter words: 2 points
         * - 6 letter words: 3 points
         * - 7 letter words: 5 points
         * - 8 letter words: 11 points
         * - 9+ letter words: 2 points per letter (formula-based)
         *
         * The 6x6 board's larger size makes finding very long words more feasible.
         * To reward exceptional 9+ letter discoveries, a special formula awards
         * 2 points per letter instead of a fixed tier value.
         *
         * Examples: 9-letter word = 18 points, 10-letter word = 20 points, etc.
         */
        fun forSuperBigBoggle6x6(): ScoringRules = ScoringRules(
            tiers = listOf(
                ScoringTier(minLength = 4, points = 1),
                ScoringTier(minLength = 5, points = 2),
                ScoringTier(minLength = 6, points = 3),
                ScoringTier(minLength = 7, points = 5),
                ScoringTier(minLength = 8, points = 11)
            ),
            usePerLetterScoring = true
        )
    }
}
