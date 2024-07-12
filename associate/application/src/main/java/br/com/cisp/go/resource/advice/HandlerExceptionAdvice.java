package br.com.cisp.go.resource.advice;

import br.com.cisp.go.exception.BadRequest;
import br.com.cisp.go.exception.BadRequestException;
import br.com.cisp.go.exception.InternalServiceErrorException;
import br.com.cisp.go.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class HandlerExceptionAdvice {
    private static final Logger log = LogManager.getLogger(HandlerExceptionAdvice.class);

    @ResponseBody
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class, InternalServiceErrorException.class})
    public ErrorResponse handleErrorNotMap(
            final Exception exception,
            final HttpServletRequest request
    ) {
        String error = "Request: " + request + " Error: " + exception;
        log.error(error);
        return new ErrorResponse(
                LocalDateTime.now(),
                request.getServletPath(),
                INTERNAL_SERVER_ERROR.value(),
                INTERNAL_SERVER_ERROR.getReasonPhrase(),
                exception.getMessage()
        );
    }

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ErrorResponse handleBadRequest(
            BadRequestException exception,
            HttpServletRequest request) {
        return new ErrorResponse(
                LocalDateTime.now(),
                request.getServletPath(),
                BAD_REQUEST.value(),
                BAD_REQUEST.getReasonPhrase(),
                exception.getMessage());
    }

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({
            BadRequest.class,
            HttpMessageNotReadableException.class,
            MethodArgumentNotValidException.class})
    public ErrorResponseBadResquest handleBadRequestException(
            final MethodArgumentNotValidException exception,
            HttpServletRequest request) {
        return new ErrorResponseBadResquest(
                LocalDateTime.now(),
                request.getServletPath(),
                BAD_REQUEST.value(),
                BAD_REQUEST.getReasonPhrase(),
                exception.getFieldError().getField(),
                exception.getFieldError().getDefaultMessage());
    }

    @ResponseBody
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse handleNotFound(
            NotFoundException exception,
            HttpServletRequest request) {
        return new ErrorResponse(
                LocalDateTime.now(),
                request.getServletPath(),
                NOT_FOUND.value(),
                NOT_FOUND.getReasonPhrase(),
                exception.getMessage());
    }
}
