package com.example.flatsharing.advertisement.domain.exception;

public class AdvertisementNotFoundException extends RuntimeException {
    public AdvertisementNotFoundException(String message) {
        super(message);
    }

}
