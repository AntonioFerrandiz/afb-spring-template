package com.afb.template.domain.dto.Citizen;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class CitizenResource {
    private Integer id;
    private String alias;
    private Date birthdayDate;
    private String firstname;
    private String lastname;
    private Boolean active;
    private Date registrationDate;
}
