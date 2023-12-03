package com.afb.template.domain.dto.Jwt;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordResource {
    private String newPassword;
    private String email;
}
