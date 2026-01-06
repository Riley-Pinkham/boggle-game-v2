package com.bogglegame

/**
 * Simple dictionary for validating words.
 * In a production app, this would load from a file or database.
 */
class Dictionary {
    private val words: Set<String>
    
    init {
        // Sample word list - in a real app, this would be loaded from a file
        words = setOf(
            "THE", "AND", "FOR", "ARE", "BUT", "NOT", "YOU", "ALL", "CAN", "HER",
            "WAS", "ONE", "OUR", "OUT", "DAY", "GET", "HAS", "HIM", "HIS", "HOW",
            "MAN", "NEW", "NOW", "OLD", "SEE", "TWO", "WAY", "WHO", "BOY", "DID",
            "ITS", "LET", "PUT", "SAY", "SHE", "TOO", "USE", "CAR", "CAT", "DOG",
            "WORD", "WORK", "WORLD", "YEAR", "GAME", "GOOD", "GREAT", "HAND", "HIGH",
            "LIFE", "LONG", "MAKE", "MANY", "OVER", "PART", "SAME", "SUCH", "TAKE",
            "THAN", "THAT", "THEM", "THEN", "THEY", "THIS", "TIME", "VERY", "WANT",
            "WELL", "WENT", "WERE", "WHAT", "WHEN", "WITH", "BACK", "BEEN", "BEFORE",
            "BEST", "BOTH", "CAME", "CALL", "COME", "COULD", "EACH", "FIND", "FIRST",
            "FROM", "GIVE", "HAVE", "HERE", "INTO", "JUST", "KNOW", "LAST", "LIKE",
            "LOOK", "MADE", "MORE", "MOST", "MUCH", "MUST", "NAME", "NEVER", "NEXT",
            "ONLY", "OTHER", "PEOPLE", "PLACE", "RIGHT", "SAID", "SOME", "STILL",
            "TELL", "THEIR", "THERE", "THESE", "THING", "THINK", "THREE", "THROUGH",
            "UNDER", "WHERE", "WHICH", "WHILE", "WILL", "WOULD", "WRITE", "YOUR",
            "ABOUT", "AFTER", "AGAIN", "ALSO", "ANOTHER", "AROUND", "BECAUSE",
            "BETWEEN", "DIFFERENT", "EVERY", "FOUND", "HOUSE", "LARGE", "LITTLE",
            "NUMBER", "POINT", "SCHOOL", "SHOULD", "SMALL", "THOSE", "UNTIL", "WORLD",
            "YEARS", "BOGGLE", "BOARD", "LETTER", "QUEST", "SUPER"
        )
    }
    
    /**
     * Checks if a word is valid (exists in the dictionary).
     */
    fun isValidWord(word: String): Boolean {
        val normalized = word.uppercase().trim()
        return normalized.length >= 3 && words.contains(normalized)
    }
    
    /**
     * Gets the word count in the dictionary.
     */
    fun size(): Int = words.size
}
