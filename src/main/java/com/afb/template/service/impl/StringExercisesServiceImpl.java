package com.afb.template.service.impl;

import com.afb.template.domain.dto.basics.Lasagna;
import com.afb.template.domain.dto.strings.LogLevels;
import com.afb.template.service.StringExercisesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class StringExercisesServiceImpl implements StringExercisesService {
    @Override
    public String logLevels(String logLine) {
        String message = "";
        message += String.format("message: %s\n", new LogLevels().message(logLine));
        message += String.format("logLevel: %s\n", new LogLevels().logLevel(logLine));
        message += String.format("reformat: %s\n", new LogLevels().reformat(logLine));
        return message;
    }
}
