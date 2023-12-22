package com.afb.template.domain.dto.Jwt;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TokenResource {
    private String token;

    public TokenResource(String token) {
        this.token = token;
    }
}
