package com.afb.template.service.impl;

import com.afb.template.domain.dto.basics.Greeter;
import com.afb.template.domain.dto.basics.Lasagna;
import com.afb.template.service.BasicsExercisesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class BasicsExercisesServiceImpl implements BasicsExercisesService {
    @Override
    public String lasagna(Integer min, Integer layers) {
        // https://exercism.org/tracks/java/exercises/lasagna/
        String message = "";
        message += String.format("expectedMinutesInOven: %d\n", new Lasagna().expectedMinutesInOven());
        message += String.format("remainingMinutesInOven: %d\n", new Lasagna().remainingMinutesInOven(min));
        message += String.format("preparationTimeInMinutes: %d\n", new Lasagna().preparationTimeInMinutes(layers));
        message += String.format("totalTimeInMinutes: %d", new Lasagna().totalTimeInMinutes(min, layers));
        return message;
    }

    @Override
    public String greeting() {
        return new Greeter().getGreeting();
    }
}
