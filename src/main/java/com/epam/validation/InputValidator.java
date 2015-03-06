package com.epam.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
//    private static final String EMAIL_REGEX = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@" +
//            "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+(?:[A-Z]{2}|com|org|net|edu|gov|mil|" +
//            "biz|info|mobi|name|aero|asia|jobs|museum)\\b";
    private static Pattern pattern;
    private static final String LOGIN_REGEX = "^[\\w]{3,32}$";
    private static final String NAT_REGEX = "\\d{8,12}";
    private static final String MONEY = "\\d+";
//    private static final String PHONE_REGEX = "[^A-Za-z]+";

    /*public static boolean phone(String phone){
        pattern = Pattern.compile(PHONE_REGEX, Pattern.UNICODE_CHARACTER_CLASS);
        return pattern.matcher(phone).matches();
    }*/

    public static boolean nameOrLogin(String login){
        pattern = Pattern.compile(LOGIN_REGEX, Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(login);
        return matcher.matches();
    }

    public static boolean natId(String natId){
        pattern = Pattern.compile(NAT_REGEX);
        Matcher matcher = pattern.matcher(natId);
        return matcher.matches();
    }

    public static boolean money(String money){
        pattern = Pattern.compile(MONEY);
        Matcher matcher = pattern.matcher(money);
        return matcher.matches();
    }

}
