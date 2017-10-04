package com.company;

public class Pesel {
    public Pesel(String pesel) {
        peselNumber = pesel;
    }

    private static String peselNumber;

    static boolean check(String peselNumber) {
        // czy dlugosc 11 znakow:
        if (peselNumber.length() != 11) {
            return false;
        }
        // czy tylko cyfry:
        for (int i = 0; i < peselNumber.length(); i++) {
            if (peselNumber.charAt(i) < 48 || peselNumber.charAt(i) > 57) {
                return false;
            }
        }
        // suma kontrolna (9×a + 7×b + 3×c + 1×d + 9×e + 7×f + 3×g + 1×h + 9×i + 7×j):
        int sum = 9 * (java.lang.Character.getNumericValue(peselNumber.charAt(0))) +
                7 * (java.lang.Character.getNumericValue(peselNumber.charAt(1))) +
                        3 * (java.lang.Character.getNumericValue(peselNumber.charAt(2))) +
                                1 * (java.lang.Character.getNumericValue(peselNumber.charAt(3)))
                                        + 9 * (java.lang.Character.getNumericValue(peselNumber.charAt(4))) + 7 * (java.lang.Character.getNumericValue(peselNumber.charAt(0))) + 3 * (java.lang.Character.getNumericValue(peselNumber.charAt(0))) + 1 * (java.lang.Character.getNumericValue(peselNumber.charAt(0))) + 9 * (java.lang.Character.getNumericValue(peselNumber.charAt(0))) + 7 * (java.lang.Character.getNumericValue(peselNumber.charAt(0)));
        return ((peselNumber.charAt(10) - '0') != (sum % 10));
    }
}
