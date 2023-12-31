package com.afb.template.domain.dto.basics;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Lasagna {
    public Integer expectedMinutesInOven() {
        return 40;
    }
    public Integer remainingMinutesInOven(Integer min) {
        return expectedMinutesInOven() - min;
    }
    public Integer preparationTimeInMinutes(Integer layers) {
        return layers * 2;
    }
    public Integer totalTimeInMinutes(Integer layers, Integer min) {
        return (layers * 2) + min;
    }
}
