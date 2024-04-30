package com.ewanthau.librarysystem.controller;

import com.ewanthau.librarysystem.dto.LibraryErrorResponse;
import com.ewanthau.librarysystem.exception.BookNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class LibraryControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public LibraryErrorResponse handleException(Exception e) {
        log.error(e.getMessage(), e);
        return LibraryErrorResponse.builder().message("System failure. Please contact support").build();
    }

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<LibraryErrorResponse> handleNotFoundException(Exception e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(LibraryErrorResponse.builder().message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
    }
}
