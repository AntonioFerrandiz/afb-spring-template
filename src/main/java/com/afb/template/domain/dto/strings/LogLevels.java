package com.afb.template.domain.dto.strings;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogLevels {
    public static String message(String logLine) {
        String message = logLine.substring(logLine.indexOf(" ", 0) + 1).trim();
        return message;
    }

    public static String logLevel(String logLine) {
        String message = "";
        return message;
    }

    public static String reformat(String logLine) {
        String message = "";
        return message;
    }
}
