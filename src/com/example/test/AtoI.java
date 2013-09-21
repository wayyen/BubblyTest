package com.example.test;

public class AtoI {
    static int radix = 10;

    public static void main(String... args) {
        for (String arg : args) {
            try {
                System.out.println((AtoI.convertStringToInt(arg)));
            } catch (NumberFormatException ne) {
                System.out.println(ne.toString());
                System.out.println(-1);
            }
        }
    }

    public AtoI() {
        this.radix = 10;
    }

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
                    if (result < max_value / radix) {
                        throw new NumberFormatException("number exceeds integer boundary.");
                    }
                    result = result * radix;
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

    /*
    * This is a strip-down replacement for Character.digit()
    * and it only supports radix of 10. 
    */
    public static int charToInt(char ch) { 
        switch (ch) {
            case '0':
                return 0;
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':   
                return 4;
            case '5':   
                return 5;
            case '6':   
                return 6;
            case '7':   
                return 7;
            case '8':   
                return 8;
            case '9':   
                return 9;
            default:
                return -1;
        }
    }
}
