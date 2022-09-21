package org.example;

import org.example.service.ShapeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        ShapeService shapeService = applicationContext.getBean("shapeService", ShapeService.class);
        shapeService.getCircle();


    }
}