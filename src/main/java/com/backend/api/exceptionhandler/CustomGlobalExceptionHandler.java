package com.backend.api.exceptionhandler;


import com.backend.api.helper.response.Error;
import com.backend.api.helper.utility.EmailUtility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;


@ControllerAdvice
public class CustomGlobalExceptionHandler {



    @Autowired
    EmailUtility emailUtility;

    private static final Logger logger = LogManager.getLogger(CustomGlobalExceptionHandler.class.getName());

    @ExceptionHandler
    public ResponseEntity<Error> dataExceptionHandler(MethodArgumentNotValidException e) {

        logger.error(e.getLocalizedMessage());

        Error error = new Error();
        error.setStatus(HttpStatus.BAD_REQUEST.value());

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            if (error.getError() == null) {
                error.setError(new ArrayList<Map<String, Object>>());
            }

            Map<String, Object> map = new LinkedHashMap<>();
            map.put("type", "validation");
            map.put("field", fieldError.getField());
            map.put("message", fieldError.getDefaultMessage());

            error.getError().add(map);
        }

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Error> dataExceptionHandler(InvalidPhotoException e) {


        logger.error(e.getLocalizedMessage());

        Error error = new Error();
        error.setStatus(HttpStatus.NOT_ACCEPTABLE.value());

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("type", "photo");
        map.put("field", "photo");
        map.put("message", "invalid photo format");

        error.setError(new ArrayList<Map<String, Object>>());
        error.getError().add(map);

        return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler
    public ResponseEntity<Error> dataExceptionHandler(InvalidDownPaymentAndInstallmentException e) {


        logger.error(e.getLocalizedMessage());

        Error error = new Error();
        error.setStatus(HttpStatus.NOT_ACCEPTABLE.value());

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("type", "payment");
        map.put("field", "downpayment amount and installment");
        map.put("message", "Downpayment or installment amount can't be greater than Plan Amount.");

        error.setError(new ArrayList<Map<String, Object>>());
        error.getError().add(map);

        return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler
    public ResponseEntity<Error> dataExceptionHandler(InvalidPaymentException e) {


        logger.error(e.getLocalizedMessage());

        Error error = new Error();
        error.setStatus(HttpStatus.NOT_ACCEPTABLE.value());

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("type", "payment");
        map.put("field", "payment amount");
        map.put("message", "payment cant be greater than due Amount and cant be less than current installment amount.");

        error.setError(new ArrayList<Map<String, Object>>());
        error.getError().add(map);

        return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler
    public ResponseEntity<Error> dataExceptionHandler(NullPointerException e) {

        logger.error(e.getLocalizedMessage());

        Error error = new Error();
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("type", "null pointer");
        map.put("field", "");
        map.put("message","null pointer exception occurred");

        error.setError(new ArrayList<Map<String, Object>>());
        error.getError().add(map);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<Error> dataExceptionHandler(NoDataFoundException e) {

        logger.error(e.getLocalizedMessage());

        Error error = new Error();
        error.setStatus(HttpStatus.NOT_FOUND.value());

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("type", "fetch");
        map.put("field", "");
        map.put("message",e.getMessage());

        error.setError(new ArrayList<Map<String, Object>>());
        error.getError().add(map);
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Error> dataExceptionHandler(HibernateException e) {

        logger.error(e.getLocalizedMessage());

        Error error = new Error();
        error.setStatus(HttpStatus.BAD_REQUEST.value());

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("type", "Sql Exception");
        map.put("field", "");
        map.put("message", e.getCause().getMessage());

        error.setError(new ArrayList<Map<String, Object>>());
        error.getError().add(map);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Error> dataExceptionHandler(InvalidDateException e) {

        logger.error(e.getLocalizedMessage());

        Error error = new Error();
        error.setStatus(HttpStatus.BAD_REQUEST.value());

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("type", "Invalid Date Exception");
        map.put("field", "");
        map.put("message", "Entered Date Is Invalid");

        error.setError(new ArrayList<Map<String, Object>>());
        error.getError().add(map);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Error> dataExceptionHandler(PlanNotCreated e) {

        logger.error(e.getLocalizedMessage());

        Error error = new Error();
        error.setStatus(HttpStatus.BAD_REQUEST.value());

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("type", "Invalid Date Exception");
        map.put("field", "");
        map.put("message", e.getMessage());

        error.setError(new ArrayList<Map<String, Object>>());
        error.getError().add(map);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Error> dataExceptionHandler(Exception e) {

        logger.error(e.getLocalizedMessage());

        e.printStackTrace();
        emailUtility.sendEmail(e);

        Error error = new Error();
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("type", "fatal error");
        map.put("field", "");
        map.put("message", "Something went wrong");

        error.setError(new ArrayList<Map<String, Object>>());
        error.getError().add(map);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
