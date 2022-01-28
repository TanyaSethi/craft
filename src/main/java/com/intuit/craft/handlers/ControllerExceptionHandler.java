package com.intuit.craft.handlers;

import com.intuit.craft.exceptions.InvalidOperationException;
import com.intuit.craft.exceptions.UserDoesNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(InvalidOperationException.class)
    public ResponseEntity<String> invalidOperationRequestedException(InvalidOperationException exception) {
        log.error("Invalid Operation Requested - ", exception);
        return new ResponseEntity<>("INVALID OPERATION", BAD_REQUEST);
    }


    @ExceptionHandler(UserDoesNotExistException.class)
    public ResponseEntity<String> userDoesNotExistExceptionException(UserDoesNotExistException exception) {
        log.error("Requested user does not exist - ", exception);
        return new ResponseEntity<>("INVALID OPERATION : Requested user does not exist", BAD_REQUEST);
    }
//    @ExceptionHandler(DataFetchException.class)
//    public ResponseEntity<String> databaseFetchException(DataFetchException exception) {
//        log.error("Error while fetching data - ", exception);
//        return new ResponseEntity<>("DATABASE_FETCH_EXCEPTION", SERVICE_UNAVAILABLE);
//    }
}
