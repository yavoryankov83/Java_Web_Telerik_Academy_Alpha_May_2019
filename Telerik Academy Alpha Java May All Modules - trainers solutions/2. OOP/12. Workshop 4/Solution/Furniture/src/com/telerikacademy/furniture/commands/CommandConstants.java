package com.telerikacademy.furniture.commands;

public class CommandConstants {
    // Error messages
    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments. Expected: %d, Received: %d";
    static final String INVALID_COMMAND_ERROR_MESSAGE = "Invalid command name: %s";
    static final String COMPANY_EXISTS_ERROR_MESSAGE = "Company %s already exists";
    static final String COMPANY_NOT_FOUND_ERROR_MESSAGE = "Company %s not found";
    static final String FURNITURE_NOT_FOUND_ERROR_MESSAGE = "Furniture %s not found";
    static final String FURNITURE_EXISTS_ERROR_MESSAGE = "Furniture %s already exists";
    static final String FURNITURE_IS_NOT_ADJUSTABLE_CHAIR_ERROR_MESSAGE = "%s is not adjustable chair";
    static final String FURNITURE_IS_NOT_CONVERTIBLE_CHAIR_ERROR_MESSAGE = "%s is not convertible chair";

    // Success messages
    static final String COMPANY_CREATED_SUCCESS_MESSAGE = "Company %s created";
    static final String FURNITURE_ADDED_SUCCESS_MESSAGE = "Furniture %s added to company %s";
    static final String FURNITURE_REMOVED_SUCCESS_MESSAGE = "Furniture %s removed from company %s";
    static final String TABLE_CREATED_SUCCESS_MESSAGE = "Table %s created";
    static final String CHAIR_CREATED_SUCCESS_MESSAGE = "Chair %s created";
    static final String CHAIR_HEIGHT_ADJUSTED_SUCCESS_MESSAGE = "Chair %s adjusted to height %.2f";
    static final String CHAIR_STATE_CONVERTED_SUCCESS_MESSAGE = "Chair %s converted";
}
