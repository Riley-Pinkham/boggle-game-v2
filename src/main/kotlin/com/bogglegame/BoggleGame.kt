package com.bogglegame

/**
 * Main Boggle Game class that manages the game state and logic.
 */
class BoggleGame(private val mode: GameMode) {
    private val board = BoggleBoard(mode)
    private val dictionary = Dictionary()
    private val foundWords = mutableSetOf<String>()
    private var score = 0
    
    /**
     * Displays the current game board.
     */
    fun displayBoard() {
        println(board.display())
    }
    
    /**
     * Attempts to submit a word.
     * Returns a message indicating success or failure.
     */
    fun submitWord(word: String): String {
        val normalized = word.uppercase().trim()
        
        if (normalized.length < 3) {
            return "Word must be at least 3 letters long!"
        }
        
        if (foundWords.contains(normalized)) {
            return "You already found '$normalized'!"
        }
        
        if (!dictionary.isValidWord(normalized)) {
            return "'$normalized' is not in the dictionary!"
        }
        
        if (!board.hasWord(normalized)) {
            return "'$normalized' cannot be formed on the board!"
        }
        
        // Valid word found!
        foundWords.add(normalized)
        val points = calculatePoints(normalized)
        score += points
        return "Great! '$normalized' is worth $points points. Total score: $score"
    }
    
    /**
     * Calculates points for a word based on its length.
     */
    private fun calculatePoints(word: String): Int {
        return when (word.length) {
            3, 4 -> 1
            5 -> 2
            6 -> 3
            7 -> 5
            else -> 11  // 8+ letters
        }
    }
    
    /**
     * Gets the current score.
     */
    fun getScore(): Int = score
    
    /**
     * Gets the list of found words.
     */
    fun getFoundWords(): Set<String> = foundWords.toSet()
    
    /**
     * Gets the current game mode.
     */
    fun getGameMode(): GameMode = mode
    
    /**
     * Displays game statistics.
     */
    fun displayStats() {
        println("\n" + "=".repeat(40))
        println("Game Statistics")
        println("=".repeat(40))
        println("Mode: ${mode.displayName}")
        println("Words found: ${foundWords.size}")
        println("Total score: $score")
        if (foundWords.isNotEmpty()) {
            println("\nYour words:")
            foundWords.sorted().forEach { word ->
                println("  - $word (${calculatePoints(word)} pts)")
            }
        }
        println("=".repeat(40))
    }
}
