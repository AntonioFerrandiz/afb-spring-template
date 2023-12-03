package com.afb.template.domain.dto.Jwt;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenResource {
    private String token;

    public TokenResource(String token) {
        this.token = token;
    }
}
