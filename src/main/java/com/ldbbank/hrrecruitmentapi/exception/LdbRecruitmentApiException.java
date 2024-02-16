package com.ldbbank.hrrecruitmentapi.exception;

import org.springframework.http.HttpStatus;

public class LdbRecruitmentApiException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public LdbRecruitmentApiException(HttpStatus status, String message) {

        super(message);
        this.status = status;
        this.message = message;


    }

//    public LDBWebsiteApiException(String message, HttpStatus status, String message1) {
//        super(message);
//        this.status = status;
//        this.message = message1;
//    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }


}
