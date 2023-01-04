package com.light.demo.junit.RunWithParameterized;

/**
 * PrimeNumberChecker.
 */
public class PrimeNumberChecker {

    public static Boolean validate(final Integer parimeNumber) {
        for (int i = 2; i < (parimeNumber / 2); i++) {
            if (parimeNumber % i == 0) {
                return false;
            }
        }
        return true;
    }
}
