package com.hiking.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Detecting wrong argument parameters and return an error message to users to be corrected it.
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    protected ResponseEntity<Object> handleRuntimeExceptions(Exception ex, WebRequest request) {
        String requestURI = ((ServletWebRequest) request).getRequest().getRequestURI();
        String message = "Please give right parameter";
        if (requestURI.contains("view_bookings_with_date")) {
            message = "Please give date parameter with this format: dd/MM/yyyy ";
        }
        return handleExceptionInternal(ex, message, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


}
