package com.todo.utils;

import org.springframework.http.HttpStatus;

public class ResponseStatus {
    
    public static ResponseModel create(String message, Object t, HttpStatus httpStatus, int httpStatusCode) {
        ResponseModel rs = new ResponseModel();
        rs.setMessage(message);
        rs.setData(t);
        rs.setStatus(httpStatus);
        rs.setStatusCode(httpStatusCode);
        return rs;
    }
    
}
