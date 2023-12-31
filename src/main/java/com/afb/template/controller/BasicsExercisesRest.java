package com.afb.template.controller;

import com.afb.template.service.BasicsExercisesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/basics_exercise")
public class BasicsExercisesRest {
    private final BasicsExercisesService basicsExercisesService;

    public BasicsExercisesRest(BasicsExercisesService basicsExercisesService) {
        this.basicsExercisesService = basicsExercisesService;
    }

    @GetMapping
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("https://exercism.org/tracks/java/concepts/basics");
    }

    @GetMapping(path = "/lasagna/{min}/{layers}", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> lasagna(@PathVariable Integer min,
                                          @PathVariable Integer layers) {
        String message = this.basicsExercisesService.lasagna(min, layers);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping(path = "/greeting", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> greeting() {
        String message = this.basicsExercisesService.greeting();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
