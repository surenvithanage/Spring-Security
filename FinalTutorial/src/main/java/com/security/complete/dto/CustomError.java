package com.security.complete.dto;

import org.springframework.http.HttpStatus;

/**
 * Project complete
 * User : suren_v
 * Date : 12/3/2019
 * Time : 9:25 AM
 */
public class CustomError {
    private HttpStatus errorCode;
    private String error;
    private String description;

    public CustomError(HttpStatus errorCode, String error, String description) {
        this.errorCode = errorCode;
        this.error = error;
        this.description = description;
    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }


    public void setErrorCode(HttpStatus errorCode) {
        this.errorCode = errorCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
