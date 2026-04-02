package com.atrdev.ecomapp.shared.util;

import java.util.InvalidPropertiesFormatException;

public class RequestUtil {
    public static Long parseUserId(String userId) throws InvalidPropertiesFormatException {
        try {
            return Long.valueOf(userId);
        } catch (NumberFormatException e) {
            throw new InvalidPropertiesFormatException("Invalid user ID format: " + userId);
        }
    }
}
