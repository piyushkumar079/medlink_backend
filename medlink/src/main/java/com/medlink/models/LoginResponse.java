package com.medlink.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponse {
    String token;
    public LoginResponse(String token) {
        this.token = token;
    }
}
