# boggle-game-v2

AI-powered Boggle game written in Kotlin, designed for easy deployment to Android.

## About

This is an AI-generated Boggle App that demonstrates AI capabilities. The original version was quickly put together in Python and was clunky. This version is written in Kotlin for easier deployment to Android.

## Game Modes

The game includes 3 different states:

1. **Classic Boggle (4x4)** - Traditional Boggle with a 4x4 grid
2. **Big Boggle (5x5)** - Expanded version with a 5x5 grid
3. **Super Big Boggle (6x6)** - Extra large version with a 6x6 grid

## Building the Project

This project uses Gradle and requires JDK 17 or higher.

### Build the project:

```bash
./gradlew build
```

### Run tests:

```bash
./gradlew test
```

### Run the application:

```bash
./gradlew run
```

Or build and run the distribution:

```bash
./gradlew installDist
./build/install/boggle-game-v2/bin/boggle-game-v2
```

## How to Play

1. Select a game mode (Classic 4x4, Big 5x5, or Super Big 6x6)
2. Find words by connecting adjacent letters (including diagonals)
3. Words must be at least 3 letters long
4. Each letter position can only be used once per word
5. Type your word and press Enter
6. Commands:
   - `quit` or `exit` - End the game and see your score
   - `board` - Display the board again
   - `stats` - Show current game statistics

## Scoring

- 3-4 letters: 1 point
- 5 letters: 2 points
- 6 letters: 3 points
- 7 letters: 5 points
- 8+ letters: 11 points

## Development Approach

While AI-generated, this version takes a more thoughtful approach with small, incremental prompts to ensure code quality and maintainability.

## Project Structure

```
boggle-game-v2/
├── src/
│   ├── main/
│   │   └── kotlin/
│   │       └── com/
│   │           └── bogglegame/
│   │               ├── BoggleBoard.kt
│   │               ├── BoggleGame.kt
│   │               ├── Dictionary.kt
│   │               ├── GameMode.kt
│   │               └── Main.kt
│   └── test/
│       └── kotlin/
│           └── com/
│               └── bogglegame/
│                   └── BoggleGameTest.kt
├── build.gradle.kts
└── settings.gradle.kts
```
 
