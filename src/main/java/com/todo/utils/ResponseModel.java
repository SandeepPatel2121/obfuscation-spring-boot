package com.todo.utils;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ResponseModel implements Serializable {
    
    private HttpStatus status;
    private int statusCode;
    private String message;
    private Object data;
    
}
