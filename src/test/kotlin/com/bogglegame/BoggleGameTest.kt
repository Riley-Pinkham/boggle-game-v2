package com.bogglegame

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class GameModeTest {
    @Test
    fun testClassicMode() {
        assertEquals(4, GameMode.CLASSIC.size)
        assertEquals("Classic Boggle (4x4)", GameMode.CLASSIC.displayName)
    }
    
    @Test
    fun testBigBoggleMode() {
        assertEquals(5, GameMode.BIG_BOGGLE.size)
        assertEquals("Big Boggle (5x5)", GameMode.BIG_BOGGLE.displayName)
    }
    
    @Test
    fun testSuperBigBoggleMode() {
        assertEquals(6, GameMode.SUPER_BIG_BOGGLE.size)
        assertEquals("Super Big Boggle (6x6)", GameMode.SUPER_BIG_BOGGLE.displayName)
    }
}

class BoggleBoardTest {
    @Test
    fun testBoardCreation() {
        val board = BoggleBoard(GameMode.CLASSIC)
        assertEquals(4, board.getSize())
    }
    
    @Test
    fun testBigBoggleBoardCreation() {
        val board = BoggleBoard(GameMode.BIG_BOGGLE)
        assertEquals(5, board.getSize())
    }
    
    @Test
    fun testSuperBigBoggleBoardCreation() {
        val board = BoggleBoard(GameMode.SUPER_BIG_BOGGLE)
        assertEquals(6, board.getSize())
    }
    
    @Test
    fun testBoardHasLetters() {
        val board = BoggleBoard(GameMode.CLASSIC)
        // Check that all positions have letters
        for (i in 0 until 4) {
            for (j in 0 until 4) {
                val letter = board.getLetter(i, j)
                assertTrue(letter.isLetter(), "Position ($i, $j) should contain a letter")
            }
        }
    }
    
    @Test
    fun testBoardDisplay() {
        val board = BoggleBoard(GameMode.CLASSIC)
        val display = board.display()
        assertTrue(display.contains("Classic Boggle (4x4)"))
        assertTrue(display.contains("|"))
    }
}

class DictionaryTest {
    @Test
    fun testValidWords() {
        val dict = Dictionary()
        assertTrue(dict.isValidWord("THE"))
        assertTrue(dict.isValidWord("WORD"))
        assertTrue(dict.isValidWord("GAME"))
    }
    
    @Test
    fun testInvalidWords() {
        val dict = Dictionary()
        assertFalse(dict.isValidWord("XYZ"))
        assertFalse(dict.isValidWord("QQQQ"))
        assertFalse(dict.isValidWord("ZZZ"))
    }
    
    @Test
    fun testCaseInsensitive() {
        val dict = Dictionary()
        assertTrue(dict.isValidWord("the"))
        assertTrue(dict.isValidWord("The"))
        assertTrue(dict.isValidWord("THE"))
    }
    
    @Test
    fun testMinimumLength() {
        val dict = Dictionary()
        assertFalse(dict.isValidWord("TO"))  // Too short, even if in dictionary
    }
    
    @Test
    fun testDictionarySize() {
        val dict = Dictionary()
        assertTrue(dict.size() > 0)
    }
}

class BoggleGameTest {
    @Test
    fun testGameCreation() {
        val game = BoggleGame(GameMode.CLASSIC)
        assertEquals(GameMode.CLASSIC, game.getGameMode())
        assertEquals(0, game.getScore())
        assertTrue(game.getFoundWords().isEmpty())
    }
    
    @Test
    fun testScoreCalculation() {
        val game = BoggleGame(GameMode.CLASSIC)
        // We can't test actual word submission easily without knowing the board,
        // but we can test that the game initializes correctly
        assertEquals(0, game.getScore())
    }
    
    @Test
    fun testFoundWordsTracking() {
        val game = BoggleGame(GameMode.CLASSIC)
        assertTrue(game.getFoundWords().isEmpty())
    }
    
    @Test
    fun testDifferentGameModes() {
        val classicGame = BoggleGame(GameMode.CLASSIC)
        val bigGame = BoggleGame(GameMode.BIG_BOGGLE)
        val superBigGame = BoggleGame(GameMode.SUPER_BIG_BOGGLE)
        
        assertEquals(GameMode.CLASSIC, classicGame.getGameMode())
        assertEquals(GameMode.BIG_BOGGLE, bigGame.getGameMode())
        assertEquals(GameMode.SUPER_BIG_BOGGLE, superBigGame.getGameMode())
    }
}
