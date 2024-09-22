package com.university.restaurant1.plat_de_jour.exception;
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
