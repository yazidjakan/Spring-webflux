package com.jakan.webflux.dto;

public record UserDto(
        String id,
        String username,
        int age,
        String email,
        String password
) {
}
