package com.example.test;

public class AtoI {
    int result = 0;
    String input = "";
    int sign = 1;
    int radix = 10;

    public static void main(String... args) {
        for (String arg : args) {
            try {
                AtoI a = new AtoI(arg);
                System.out.println((a.convertStringToInt()));
            } catch (NumberFormatException ne) {
                System.out.println(-1);
            }
        }
    }

    public AtoI(String input) {
        this.input = input;
        this.radix = 10;
    }

    private int convertStringToInt() {
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
                    // set negative here
                    sign = -1;
                } else if ((digit=charToInt(input.charAt(i))) != -1) {
                    result += digit;
                } else {
                    throw new NumberFormatException("invalid string 1.");
                }
            } else {
                // process the rest
                if ((digit=charToInt(input.charAt(i))) != -1 ) {
                    result = result * radix + digit; 
                } else {
                    throw new NumberFormatException("invalid string 2.");
                }
            }
        }
        return sign*result;
    }

    /*
    * This is a strip-down replacement for Character.digit()
    * and it only supports radix of 10. 
    */
    private int charToInt(char ch) { 
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
