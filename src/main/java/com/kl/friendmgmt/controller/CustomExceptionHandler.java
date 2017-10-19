package com.kl.friendmgmt.controller;

import com.kl.friendmgmt.exception.connection.ConnectionBlockedException;
import com.kl.friendmgmt.exception.connection.ConnectionExistException;
import com.kl.friendmgmt.exception.subscription.BlockExistException;
import com.kl.friendmgmt.exception.subscription.SubscriptionExistException;
import com.kl.friendmgmt.exception.userinfo.UserInfoExistException;
import com.kl.friendmgmt.exception.userinfo.UserInfoNotExistException;
import com.kl.friendmgmt.json.response.ApiErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        ApiErrorResponse apiError = new ApiErrorResponse(errors);
        return handleExceptionInternal(ex, apiError, headers, status, request);
    }

    @ExceptionHandler({ UserInfoNotExistException.class })
    public ResponseEntity<Object> handleEntityNotExistsException(Exception ex,WebRequest request){
        List<String> errors = new ArrayList<String>();
        errors.add(ex.getMessage());
        ApiErrorResponse apiError=new ApiErrorResponse(errors);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ UserInfoExistException.class, ConnectionExistException.class, BlockExistException.class, SubscriptionExistException.class})
    public ResponseEntity<Object> handleEntityExistsException(Exception ex,WebRequest request){
        List<String> errors = new ArrayList<String>();
        errors.add(ex.getMessage());
        ApiErrorResponse apiError=new ApiErrorResponse(errors);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({ ConnectionBlockedException.class})
    public ResponseEntity<Object> handleConnectionBlockedException(Exception ex,WebRequest request){
        List<String> errors = new ArrayList<String>();
        errors.add(ex.getMessage());
        ApiErrorResponse apiError=new ApiErrorResponse(errors);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }
}
