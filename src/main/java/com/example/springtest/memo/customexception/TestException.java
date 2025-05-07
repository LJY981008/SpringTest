package com.example.springtest.memo.customexception;

import org.springframework.http.HttpStatus;

    public class TestException extends Exception{
        private static final long serialVersionUID = 44444444444444L;

        private ExceptionTestEnum.Exceptions exceptions;
        private HttpStatus httpStatus;

        public TestException(ExceptionTestEnum.Exceptions exceptions, HttpStatus httpStatus, String message){
            super(exceptions.toString() + message);
            this.exceptions = exceptions;
            this.httpStatus = httpStatus;
        }

        public ExceptionTestEnum.Exceptions getExceptions() {
            return exceptions;
        }
        public int getHttpStatusCode(){
            return httpStatus.value();
        }
        public String getHttpStatusType(){
            return httpStatus.getReasonPhrase();
        }
        public HttpStatus getHttpStatus(){
            return httpStatus;
        }
    }
