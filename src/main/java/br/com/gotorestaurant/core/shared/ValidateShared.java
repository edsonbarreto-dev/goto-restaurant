package br.com.gotorestaurant.core.shared;

import br.com.gotorestaurant.core.exceptions.CodeAreaNullException;
import br.com.gotorestaurant.core.exceptions.CountryCodeNullException;
import br.com.gotorestaurant.core.exceptions.PhoneNumberMinLengthException;
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
}
