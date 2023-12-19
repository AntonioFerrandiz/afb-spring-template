package com.afb.template.domain.dto.Citizen;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class CreateCitizenResource {
    private String alias;
    private Date birthdayDate;
    private String firstname;
    private String lastname;
    private String fullname;
    private String email;
    private String password;
    private Boolean active;
    private Date registrationDate;
}
