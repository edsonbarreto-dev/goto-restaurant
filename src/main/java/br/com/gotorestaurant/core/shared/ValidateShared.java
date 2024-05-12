package br.com.gotorestaurant.core.shared;

import br.com.gotorestaurant.core.exceptions.*;
import br.com.gotorestaurant.core.records.Phone;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ValidateShared {

    private ValidateShared(){}

    public static boolean validateEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return !matcher.matches();
    }

    public static void verifyPhone(Phone phone) {
        int minLengthPhoneNumber = 9;
        if (phone.countryCode() == null) throw new CountryCodeNullException();
        if (phone.codeArea().isBlank()) throw new CodeAreaNullException();
        if (phone.number().toString().length() < minLengthPhoneNumber) throw new PhoneNumberMinLengthException();
    }

    public static void validateAccountName(String accountName) {
        int minLengthAccountName = 3;
        if (accountName.length() < minLengthAccountName) throw new AccountMinLengthException();
    }

    public static void validateFullUrlPlatform(String fullUrlPlatform) {
        String regex = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fullUrlPlatform);
        if (!matcher.matches()) throw new FullUrlPlatformException();
    }
}
