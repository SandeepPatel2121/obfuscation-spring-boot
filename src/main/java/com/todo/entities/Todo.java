package com.todo.entities;

import com.todo.enums.TodoStatus;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "todo")
@Getter
@Setter
public class Todo implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "title")
    private String title;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdDate;
    
    @Column(name = "todo_status")
    @Enumerated(EnumType.ORDINAL)
    private TodoStatus todoStatus;
    
}
