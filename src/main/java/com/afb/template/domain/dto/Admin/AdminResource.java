package com.afb.template.domain.dto.Admin;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class AdminResource {
    private Integer id;
    private String alias;
    private String firstname;
    private String lastname;
    private Boolean active;
    private Date registrationDate;
}
