package com.ecommerce.Shopify.controllers;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice
public class ExceptionHandlerController {

    //    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(ChangeSetPersister.NotFoundException exception) {

        System.out.println("Not Found Exception Handler");
        return ResponseEntity.notFound().build();
    }
}