package com.afb.template.domain.dto.basics;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Greeter {
    public String getGreeting() {
        return "Hello, World!";
    }

}
