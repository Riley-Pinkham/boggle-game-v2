package com.bogglegame

import kotlin.random.Random

/**
 * Represents a Boggle game board with configurable size.
 */
class BoggleBoard(private val mode: GameMode) {
    private val size = mode.size
    private val board: Array<CharArray> = Array(size) { CharArray(size) }
    
    // Letter distribution based on standard Boggle dice
    private val letters = "AAAAAAAABBCCDDDDEEEEEEEEEEEEFFGGGHHIIIIIIIIIJKLLLLMMNNNNNNOOOOOOOOPPQRRRRRRSSSSTTTTTTUUUUVVWWXYYZ"
    
    init {
        generateBoard()
    }
    
    /**
     * Generates a random Boggle board.
     */
    private fun generateBoard() {
        for (i in 0 until size) {
            for (j in 0 until size) {
                board[i][j] = letters[Random.nextInt(letters.length)]
            }
        }
    }
    
    /**
     * Gets the letter at the specified position.
     */
    fun getLetter(row: Int, col: Int): Char {
        require(row in 0 until size && col in 0 until size) { "Position out of bounds" }
        return board[row][col]
    }
    
    /**
     * Returns the size of the board.
     */
    fun getSize(): Int = size
    
    /**
     * Displays the board in a formatted grid.
     */
    fun display(): String {
        val sb = StringBuilder()
        sb.appendLine("\n${mode.displayName}")
        sb.appendLine("=".repeat(size * 4 + 1))
        
        for (i in 0 until size) {
            sb.append("| ")
            for (j in 0 until size) {
                val letter = board[i][j]
                sb.append(if (letter == 'Q') "Qu" else "$letter ")
                sb.append(" ")
            }
            sb.appendLine("|")
        }
        sb.appendLine("=".repeat(size * 4 + 1))
        return sb.toString()
    }
    
    /**
     * Checks if a word exists on the board by finding a valid path.
     */
    fun hasWord(word: String): Boolean {
        if (word.length < 3) return false
        
        val normalizedWord = word.uppercase().replace("QU", "Q")
        
        for (i in 0 until size) {
            for (j in 0 until size) {
                val visited = Array(size) { BooleanArray(size) }
                if (dfs(i, j, normalizedWord, 0, visited)) {
                    return true
                }
            }
        }
        return false
    }
    
    /**
     * Depth-first search to find a word path on the board.
     */
    private fun dfs(row: Int, col: Int, word: String, index: Int, visited: Array<BooleanArray>): Boolean {
        if (index == word.length) return true
        
        if (row !in 0 until size || col !in 0 until size || visited[row][col]) {
            return false
        }
        
        if (board[row][col] != word[index]) {
            return false
        }
        
        visited[row][col] = true
        
        // Check all 8 adjacent cells
        val directions = listOf(
            -1 to -1, -1 to 0, -1 to 1,
            0 to -1,           0 to 1,
            1 to -1,  1 to 0,  1 to 1
        )
        
        for ((dr, dc) in directions) {
            if (dfs(row + dr, col + dc, word, index + 1, visited)) {
                visited[row][col] = false
                return true
            }
        }
        
        visited[row][col] = false
        return false
    }
    
    /**
     * Gets all letters on the board as a flat string (for testing/debugging).
     */
    fun getAllLetters(): String {
        return board.joinToString("") { it.joinToString("") }
    }
}
