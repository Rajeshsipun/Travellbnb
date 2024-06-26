package com.travelbnb.travelbnb.exception;

import com.travelbnb.travelbnb.dto.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ExceptionHanding {


    @ExceptionHandler(ResourceNotException.class)
    public ResponseEntity<ErrorDetails>resourceNotException(
            ResourceNotException ex,
            WebRequest webRequest
    ){
        ErrorDetails errorDetails = new ErrorDetails(
                ex.getMessage(),
                new Date(),
                webRequest.getDescription(true)
        );
        return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String>Exception(
            Exception ex
    ){
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
