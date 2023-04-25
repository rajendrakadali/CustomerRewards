package com.sravani.cr.configuration.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtTokenRequest {
    String username;
    String password;
}
