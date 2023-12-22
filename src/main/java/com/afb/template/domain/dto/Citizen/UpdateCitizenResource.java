package com.afb.template.domain.dto.Citizen;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class UpdateCitizenResource {
    private String alias;
    private Date birthdayDate;
}
