package com.hospitalapplication2.exceptions;

import com.hospitalapplication2.entity.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class HospitalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { RecordNotAvailableException.class,Exception.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        ApiError apiError = new ApiError();
        apiError.setMessage(ex.getMessage());
        apiError.setStatus(HttpStatus.NOT_FOUND);
//        apiError.setErrors(ex.getCause().getMessage());

        return handleExceptionInternal(ex,apiError, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}