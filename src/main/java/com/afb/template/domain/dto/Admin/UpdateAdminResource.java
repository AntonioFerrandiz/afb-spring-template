package com.afb.template.domain.dto.Admin;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateAdminResource {
    private String alias;
}
