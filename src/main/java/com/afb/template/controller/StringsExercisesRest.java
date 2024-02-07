package com.afb.template.controller;

import com.afb.template.service.StringExercisesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/strings_exercises")
public class StringsExercisesRest {
    private final StringExercisesService stringExercisesService;

    public StringsExercisesRest(StringExercisesService stringExercisesService) {
        this.stringExercisesService = stringExercisesService;
    }


    @GetMapping
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("https://exercism.org/tracks/java/concepts/strings");
    }

    @GetMapping(path = "/logLevels/{logLine}/", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> logLevels(@PathVariable String logLine) {
        String message = this.stringExercisesService.logLevels(logLine);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
