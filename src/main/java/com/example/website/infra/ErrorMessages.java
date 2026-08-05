package com.example.website.infra;

public final class ErrorMessages {

    private ErrorMessages(){}

    public static final String USER_NOT_FOUND =
            "User not found with id: %d";

    public static final String VALIDATION_FAILED =
            "Validation failed. Please check the fields below.";

    public static final String EMAIL_ALREADY_EXISTS =
            "The given email '%s' is already registered. Please choose a different one";
}
