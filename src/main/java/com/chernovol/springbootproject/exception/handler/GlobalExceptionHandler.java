package com.chernovol.springbootproject.exception.handler;

import com.chernovol.springbootproject.exception.ConflictException;
import com.chernovol.springbootproject.exception.DepartureNotFoundException;
import com.chernovol.springbootproject.exception.WorkerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler({
            DepartureNotFoundException.class,
            ConflictException.class
    })
    public ResponseEntity<Object> notFoundDepartureByName(DepartureNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ResponseBody
    @ExceptionHandler({
            WorkerNotFoundException.class
    })
    public ResponseEntity<Object> notFoundWorkerByName(WorkerNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ResponseBody
    @ExceptionHandler({
            RuntimeException.class
    })
    public ResponseEntity<Object> runtimeException(RuntimeException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}

