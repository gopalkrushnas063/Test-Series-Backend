package com.example.Test.Series.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BannerException.class)
    public ResponseEntity<MyErrorDetails> StudentExceptionHandler(BannerException exp, WebRequest req){
        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), exp.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<MyErrorDetails> UserExceptionHandler(UserException exp, WebRequest req){
        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), exp.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExamCardExceptions.class)
    public ResponseEntity<MyErrorDetails> ExamCardExceptionHandler(ExamCardExceptions exp, WebRequest req){
        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), exp.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<MyErrorDetails> runtimeExceptionHandler(RuntimeException exp, WebRequest req){
        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), exp.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MyErrorDetails> IllegalArgumentExceptionHandler(IllegalArgumentException exp, WebRequest req){
        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), exp.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyErrorDetails> ExceptionHandler(Exception exp, WebRequest req){
        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), exp.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
}