package com.example.demo.eventListener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AnnotationDrivenEventListener {
    @EventListener
    public void handleContextStart(CustomSpringEvent cse) {
        System.out.println("Handling context started event." + cse.getMessage());
    }
}
