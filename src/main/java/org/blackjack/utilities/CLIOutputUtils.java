package org.blackjack.utilities;

public class CLIOutputUtils {

    // TODO create test
    public static String generateOutputString(String value, String lineSeparator) {
        if (lineSeparator == null) {
            return value;
        }
        return lineSeparator + "\n" + value + "\n" + lineSeparator;
    }

    // TODO create test
    public static String generateMultiLineOutputString(String... value) {
        StringBuilder outputString = new StringBuilder();
        for (String v : value) {
            outputString.append(v).append("\n");
        }
        return outputString.toString();
    }

    // TODO create a String format utility
}
