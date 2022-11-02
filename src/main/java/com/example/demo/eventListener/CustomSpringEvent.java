
package com.example.demo.eventListener;

import org.springframework.context.ApplicationEvent;

public class CustomSpringEvent extends ApplicationEvent {
    private final String message;

    public CustomSpringEvent(Object source, String message) {
        super(source);
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}