package com.sikl3gend;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {
}