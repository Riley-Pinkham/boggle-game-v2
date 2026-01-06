package com.bogglegame

/**
 * Main entry point for the Boggle Game application.
 */
fun main() {
    println("=".repeat(50))
    println("         Welcome to Boggle Game v2!")
    println("=".repeat(50))
    println()
    
    var playing = true
    
    while (playing) {
        val mode = selectGameMode()
        if (mode == null) {
            playing = false
            continue
        }
        
        playGame(mode)
        
        println("\nWould you like to play again? (yes/no)")
        val response = readlnOrNull()?.trim()?.lowercase()
        playing = response == "yes" || response == "y"
    }
    
    println("\nThanks for playing Boggle! Goodbye!")
}

/**
 * Displays the game mode selection menu and returns the selected mode.
 */
fun selectGameMode(): GameMode? {
    while (true) {
        println("\nSelect a game mode:")
        println("1. ${GameMode.CLASSIC.displayName}")
        println("2. ${GameMode.BIG_BOGGLE.displayName}")
        println("3. ${GameMode.SUPER_BIG_BOGGLE.displayName}")
        println("4. Exit")
        print("\nEnter your choice (1-4): ")
        
        when (readlnOrNull()?.trim()) {
            "1" -> return GameMode.CLASSIC
            "2" -> return GameMode.BIG_BOGGLE
            "3" -> return GameMode.SUPER_BIG_BOGGLE
            "4" -> return null
            else -> println("Invalid choice. Please try again.")
        }
    }
}

/**
 * Plays a single game of Boggle.
 */
fun playGame(mode: GameMode) {
    val game = BoggleGame(mode)
    
    println("\n" + "=".repeat(50))
    println("Starting ${mode.displayName}!")
    println("=".repeat(50))
    println("\nRules:")
    println("- Find words by connecting adjacent letters (including diagonals)")
    println("- Words must be at least 3 letters long")
    println("- Each letter position can only be used once per word")
    println("- Type 'quit' to end the game and see your score")
    println("- Type 'board' to see the board again")
    println("- Type 'stats' to see your current statistics")
    
    game.displayBoard()
    
    var gameActive = true
    
    while (gameActive) {
        print("\nEnter a word (or command): ")
        val input = readlnOrNull()?.trim() ?: ""
        
        when (input.lowercase()) {
            "quit", "exit" -> {
                gameActive = false
            }
            "board" -> {
                game.displayBoard()
            }
            "stats" -> {
                game.displayStats()
            }
            "" -> {
                println("Please enter a word or command.")
            }
            else -> {
                val result = game.submitWord(input)
                println(result)
            }
        }
    }
    
    println("\nGame Over!")
    game.displayStats()
}
