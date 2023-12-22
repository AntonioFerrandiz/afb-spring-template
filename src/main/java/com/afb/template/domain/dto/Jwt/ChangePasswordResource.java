package com.afb.template.domain.dto.Jwt;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChangePasswordResource {
    private String newPassword;
    private String email;
}
