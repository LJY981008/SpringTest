package com.example.springtest.memo.customexception;

import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ExceptionHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);
    public ResponseEntity<Map<String, String>> handler(TestException e){
        HttpHeaders responseHeader =  new HttpHeaders();

        Map<String, String> map = new HashMap<>();
        map.put("error type", e.getHttpStatusType());
        map.put("error code", Integer.toString(e.getHttpStatusCode()));
        map.put("message", e.getMessage());
        return new ResponseEntity<>(map, responseHeader, e.getHttpStatus());
    }
}
