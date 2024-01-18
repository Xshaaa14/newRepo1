package com.blogger.exception;

import com.blogger.payload.ErrorDetails;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
  //ResponseEntityExceptionHandle- this becomes like a global catch block


    @ExceptionHandler(ResourceNotFoundException.class) //HANDLE all excep related to this class
    public ResponseEntity<ErrorDetails> resourceNotFoundException(
            //when an excep occurs it will go to this method and ths method
            //will put the in error detail obj and give it to postman response

            ResourceNotFoundException exception,
            WebRequest webRequest
             //it will give details where exception occurs

    ){

        ErrorDetails errorDetails= new ErrorDetails(new Date(),exception.getMessage(),webRequest.getDescription(true));
                                                                                         //CAN ALSO SET HERE false it wont show client details
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(Exception.class) //it will handle all the exceptions
    public ResponseEntity<ErrorDetails> handleGlobalException(
            Exception exception,
            WebRequest webRequest

    ){

        ErrorDetails errorDetails= new ErrorDetails(new Date(),exception.getMessage(),webRequest.getDescription(true));
        //CAN ALSO SET HERE false it wont show client details
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
