# How to Play
### Run via a Versioned Jar and Command Line
1. Download jar of version of your choice to a folder on your computer here: https://github.com/cpaj2013/blackjack/releases.
2. Make sure your command line is pointed to the directory your jar is in and run the following:
```
java -jar ./<jar name>.jar
```
3. Enjoy the game!

# About the File Structure
```
card-game/
│── src/
│   ├── main/
│   │   ├── java/com/example/cardgame/
│   │   │   ├── model/         # Game-related data structures (e.g., Card, Player, Deck)
│   │   │   ├── service/       # Core game logic (e.g., GameService, TurnManager)
│   │   │   ├── controller/    # Handles user inputs or API requests (for later transition)
│   │   │   ├── ui/            # CLI interaction logic (later replaced by web front end)
│   │   │   ├── CardGameApp.java   # Main entry point
│   │   ├── resources/         # Config files, if needed
│   ├── test/                  # Unit tests for game logic
│── pom.xml (or build.gradle)  # Project dependencies & build config
│── README.md                  # Overview of the project
```
