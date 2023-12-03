package com.afb.template.domain.dto.Citizen;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UpdateCitizenResource {
    private String alias;
    private Date birthdayDate;
}
