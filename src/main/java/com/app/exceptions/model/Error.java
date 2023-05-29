package com.app.exceptions.model;

import java.util.AbstractMap;
import java.util.Map;

public enum Error {
    DUPLICATE_EMAIL("DUPLICATE_EMAIL", "User with this email already exists. Please use another email."),
    DUPLICATE_PHONE_NUMBER("DUPLICATE_PHONE_NUMBER", "User with this phone number already exists. Please use another phone number."),
    PASSWORD_NOT_MATCH_REQUIREMENTS("PASSWORD_NOT_MATCH_REQUIREMENTS", ""),
    EMAIL_NOT_MATCH_REQUIREMENTS("EMAIL_NOT_MATCH_REQUIREMENTS", ""),
    PHONE_NUMBER_NOT_MATCH_REQUIREMENTS("PHONE_NUMBER_NOT_MATCH_REQUIREMENTS", "");

    private final String error;
    private final String message;

    Error(String key, String value) {
        this.error = key;
        this.message = value;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public Map.Entry<String, String> getBoth() {
        return new AbstractMap.SimpleEntry<>(error, message);
    }
}
