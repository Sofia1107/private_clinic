package com.app.util;

public class ValidationUtil {
    /**
     * Checks if a password meets the specified password pattern.
     * <p>
     * (?=.*[A-Z]) - at least one uppercase letter
     * (?=.*\d) - at least one digit
     * (?=.*[@#$%^&+=!]) - at least one special character
     * (?=.*[a-z]) - at least one lowercase letter
     * .{8,} - minimum length of 8 characters
     *
     * @param pass the password to check
     * @return true if the password meets the pattern, false otherwise
     */
    public static boolean validatePassword(String pass) {
        String pattern = "(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=.*[a-z]).{8,}";
        return pass.matches(pattern);
    }

    public static boolean validateEmail(String email) {
        String pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(pattern);
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        String pattern = "^\\+?3?8?(0\\d{9})$";
        return phoneNumber.matches(pattern);
    }
}
