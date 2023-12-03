package com.afb.template.domain.dto.Admin;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateAdminResource {
    private String alias;
    private String firstname;
    private String lastname;
    private String fullname;
    private String email;
    private String password;
    private Boolean active;
    private Date registrationDate;
}
