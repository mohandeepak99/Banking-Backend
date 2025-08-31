package com.wipro.mohan.enumtype;

import com.wipro.mohan.exception.InvalidGenreException;

public enum MovieGenre {
    ACTION,
    ROMANCE,
    MYTHO,
    COMEDY;

    public static MovieGenre from(String value) {
        try {
            return MovieGenre.valueOf(value.trim().toUpperCase());
        } catch (Exception e) {
            throw new InvalidGenreException(
                "Invalid genre: " + value + ". Allowed: ACTION, ROMANCE, MYTHO, COMEDY"
            );
        }
    }
}
