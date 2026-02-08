package org.blackjack.enums;

import lombok.Getter;

@Getter
public enum Command {
    // TODO adjust Menu Commands and Gameplay commands so there is a way to check just 1
    //  (i.e. dont need to check both menu and gameplay classes)

    // Menu commands
    PLAY("p", "play", CommandType.MENU),
    EXIT_MENU("e", "exit", CommandType.MENU),

    // Gameplay commands
    SPLIT("s", "split", CommandType.GAMEPLAY),
    STAND("x", "stand", CommandType.GAMEPLAY),
    HIT("h", "hit", CommandType.GAMEPLAY),
    EXIT_GAMEPLAY("e", "exit", CommandType.GAMEPLAY);

    public enum CommandType {
        MENU,
        GAMEPLAY
    }

    private final String key;      // short input
    private final String word;     // full word input
    private final CommandType type;

    Command(String key, String word, CommandType type) {
        this.key = key;
        this.word = word;
        this.type = type;
    }

    /**
     * Check if input is valid for a given command type
     */
    public static boolean isValidInput(String input, CommandType type) {
        input = input.toLowerCase();
        for (Command cmd : values()) {
            if (cmd.type == type && (cmd.key.equals(input) || cmd.word.equals(input))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return the matching Command for input and type, or null if invalid
     */
    public static Command fromInput(String input, CommandType type) {
        input = input.toLowerCase();
        for (Command cmd : values()) {
            if (cmd.type == type && (cmd.key.equals(input) || cmd.word.equals(input))) {
                return cmd;
            }
        }
        return null;
    }
}
