package com.todo.enums;

public enum TodoStatus {
    
    NEW("New"),
    DELETE("Delete");
    
    private final String desc;

    private TodoStatus(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return this.desc;
    }
    
}
