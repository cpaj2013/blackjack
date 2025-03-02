# Changelog

## [v0.3.0-alpha] - March 1, 2025
### Added
- Cleaned up command line output.

### Fixed
- Fixed issue where dealer score shows before player turn is over.
- Fixed issue where the player busted, but it still said the player won.

### Known Issues
- Invalid character input can cause some unwanted behavior.
- Win/loss tracking will come in a future release.
- When a dealer has score of 17 and players score is higher, but less than 21 give auto win.

## [v0.2.0-alpha] - February 28, 2025
### Added
- Added functionality to have dealers first card flipped until its turn.

### Fixed
- Fixed issue where dealer won, despite busting.
- Removed Bust messages with score difference, which caused confusion for wins and losses.

### Known Issues
- Win/loss tracking will come in a future release.
- When a dealer has score of 17 and players score is higher, but less than 21 give auto win.

## [v0.1.0-alpha] - February 28, 2025
### Added
- Basic blackjack logic with a 52-card shuffled deck.
- Turn-based gameplay allowing players to draw and play cards with computer.

### Fixed
- N/A

### Known Issues
- Win/loss tracking will come in a future release.
- Hidden dealer card
- When a dealer has score of 17 and players score is higher, but less than 21 give auto win.