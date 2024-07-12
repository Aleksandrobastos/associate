package br.com.cisp.go.resource.advice;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ErrorResponseBadResquest(
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime date,
        String path,
        Integer status,
        String error,
        String field,
        String message
) {
}
