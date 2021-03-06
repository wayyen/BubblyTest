package com.example.test;

/**
 * This class converts a string to integer
 */
public class AtoI {
    final static int RADIX = 10;

    public static void main(String... args) {
        for (String arg : args) {
            try {
                System.out.println(
                    String.format(
                        "The value is: %d", 
                        (AtoI.convertStringToInt(arg))
                    )
                );
            } catch (NumberFormatException ne) {
                // System.out.println(ne.toString());
                System.out.println(
                    String.format(
                        "The value is: %d", 
                        -1
                    )
                );
            }
        }
    }

    public AtoI() {
    }

    /**
     * This method calls charToInt to decodes each character in a 
     * input string into integer digits, it thens iterates and
     * multiply the result with radix value and adding the decoded
     * digit to form the final converted integer value.
     */
    public static int convertStringToInt(String input) {
        int result = 0;
        int sign = -1;
        int max_value = -Integer.MAX_VALUE;
        if (input == null) {
            throw new NumberFormatException("null string.");
        }
        if (input.isEmpty()) {
            throw new NumberFormatException("empty string.");
        }
        int digit = 0;
        for(int i=0; i<input.length(); i++) {
            if (i==0) { // first char
                if (input.charAt(i)=='-') {
                    sign = 1;
                    max_value = Integer.MIN_VALUE;
                    if (input.length() == 1) {
                        throw new NumberFormatException("invalid string.");
                    }
                } else if ((digit=charToInt(input.charAt(i))) != -1) {
                    result -= digit;
                } else {
                    throw new NumberFormatException("invalid string.");
                }
            } else {
                // process the rest
                if ((digit=charToInt(input.charAt(i))) != -1 ) {
                    if (result < max_value / RADIX) {
                        throw new NumberFormatException("number exceeds integer boundary.");
                    }
                    result = result * RADIX;
                    if (result < max_value + digit) {
                        throw new NumberFormatException("number exceeds integer boundary.");
                    }
                    result = result - digit; 
                } else {
                    throw new NumberFormatException("invalid string.");
                }
            }
        }
        return sign*result;
    }

    /**
     * Converts a character to base10 digit
     * Supports only radix = 10
     */
    public static int charToInt(char ch) {
        if (ch >= '0' && ch <= '9') {
            return ch - '0';
        } else {
            return -1;
        }
    }
}
