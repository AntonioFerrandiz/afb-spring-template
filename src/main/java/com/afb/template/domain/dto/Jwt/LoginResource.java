package com.afb.template.domain.dto.Jwt;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
public class LoginResource {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
