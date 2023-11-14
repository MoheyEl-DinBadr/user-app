package com.example.utils;

import com.example.dto.UserPostDto;
import jakarta.ws.rs.BadRequestException;

public class ValidationUtil {
    public static void validateUserPostDto(UserPostDto dto) {
        validateEmail(dto.getEmail());
        validateFirstName(dto.getFirstName());
        validateFirstLastName(dto.getLastName());
    }

    public static void validateFirstLastName(String lastName) {
        if (lastName == null || lastName.isBlank()) throw new BadRequestException("Last name can not be empty or null");
    }

    public static void validateFirstName(String firstName) {
        if (firstName == null || firstName.isBlank()) throw new BadRequestException("First name can not be empty or null");
    }

    public static void validateEmail(String email) {
        validateEmailIsNullOrEmpty(email);
        validateEmailFormat(email);
    }

    private static void validateEmailFormat(String email) {
        if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) throw new BadRequestException("Invalid email format");
    }

    private static void validateEmailIsNullOrEmpty(String email) {
        if (email == null || email.isBlank()) throw new BadRequestException("Email can't be null or Empty");
    }


    private ValidationUtil() {
    }
}
