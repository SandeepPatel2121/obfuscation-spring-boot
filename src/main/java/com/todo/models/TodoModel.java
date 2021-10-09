package com.todo.models;

import java.util.Calendar;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoModel {
    
    private Long id;
    private String title;
    private String description;
    private String createdDate;
    private Calendar formatedDate;
    private String todoStatus;
    
}
