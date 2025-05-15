package com.example.hiringapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.Map;

@RestController
public class GlobalException {
    @ExceptionHandler(CandidateNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleNotFound(CandidateNotFoundException ex) {
        Map<String,Object> response = Map.of(
                "timestamp" , LocalTime.now().toString(),
                "status", HttpStatus.NOT_FOUND.value(),
                "error","Candidate Not Found",
                "message", ex.getMessage(),
                "path", "/candidates/"+ ex.getMessage()

        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String,Object>> handleBadRequest(BadRequestException ex){
        Map<String,Object> response = Map.of(
                "timestamp",LocalTime.now().toString(),
                "status",HttpStatus.BAD_REQUEST,
                "error","Invalid Status",
                "message",ex.getMessage(),
                "path", "/candidates/"+ ex.getMessage()

        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

}
