package com.ibrahimmohurlu.listing_service.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException() {
    }
}
