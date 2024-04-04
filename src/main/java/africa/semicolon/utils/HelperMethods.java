package africa.semicolon.utils;

import africa.semicolon.exceptions.IllegalArgumentException;

public class HelperMethods {
    public static void validateIfEmpty(String request) {
        if (request.isEmpty()) throw new IllegalArgumentException("Empty request");
    }

    public static void validateIfNull(String request) {
        if (request == null) throw new NullPointerException("Not a legal argument");
    }
}
