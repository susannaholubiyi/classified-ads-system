package africa.semicolon.utils;

import africa.semicolon.data.models.Seller;
import africa.semicolon.exceptions.IllegalArgumentException;
import africa.semicolon.exceptions.IncorrectPhoneNumberLength;
import africa.semicolon.exceptions.InvalidInputException;
import africa.semicolon.exceptions.SellerDoesNotExistException;

import java.util.Optional;
import java.util.regex.Pattern;

public class GlobalHelpers {
    public static void validateIfEmpty(String request) {
        if (request.isEmpty()) throw new IllegalArgumentException(String.format("%s is empty", request));
    }

    public static void validateIfNull(String request) {
        if (request == null) throw new NullPointerException(String.format("%s is not a valid argument", request));
    }
    public static boolean isNumeric(String productPrice) {
        String regex = "\\d+";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(productPrice).matches();
    }
    public static void validatePhoneNumber(String phoneNumber) {
        String regex = "\\d+";
        Pattern pattern = Pattern.compile(regex);
        boolean condition = pattern.matcher(phoneNumber).matches();
        if (condition) {
            validateLengthOf(phoneNumber);
        }

        else throw new InvalidInputException("phone number should be digits");
    }


    private static void validateLengthOf(String phoneNumber) {
        if (phoneNumber.length() != 11){
            throw new IncorrectPhoneNumberLength("Phone number should be 11 digits");
        }
    }
    public static String filePath = "/home/user/Desktop/classifiedAdsSystem/src/main/java/africa/semicolon/AdsImages/fendiBag.jpg";
}
