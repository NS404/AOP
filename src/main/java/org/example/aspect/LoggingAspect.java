package org.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {



    @Before("execution(public String getName())")
    public void LoggingAdvice(){
        System.out.print("Advice run. Get Method called");
    }


    @Before("execution(* org.example.model.*.get*(..))")
    public void secondAdvice(){
        System.out.print("Advice run. Get Method called");
    }

    @Before("allGetters()")
    public void thirdAdvice(){
        System.out.print("Advice run. Get Method called");
    }

    @Before("allGetters() && allCircleMethods()")  //chaining pointcuts
    public void fourthAdvice(JoinPoint joinPoint){ // JoinPoint contains data about the point advised
        System.out.print("Advice run" + joinPoint.getTarget());

    }

    @AfterReturning(pointcut = "args(name)", returning = "returnObj")
    public void afterReturningAdvice(String name, Object returnObj){
        System.out.println("a method has been called with " + name + "parameter, returning " + returnObj);
    }

    @AfterThrowing(pointcut = "args(name)", throwing = "ex")
    public void exceptionAdvice(String name, Exception ex){
        System.out.print("An exception has been thrown " + ex);
    }

    @Around("@annotation(org.example.aspect.Loggable)")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {


        Object returnObj = null;


        try {

            // before the advised method executes

            returnObj = proceedingJoinPoint.proceed();

            // after the advised method executes


        } catch (Throwable e) {
           throw new RuntimeException();
        }


        return returnObj;

    }

    @Before("args(String)")
    public void stringArgumentMethod(){
        System.out.println("A method that takes String argument has been called");
    }

    @Before("args(name)")
    public void nameParameterMethod(String name){
        System.out.print("A method that takes name argument has been called. The value is " + name);

    }


    @Pointcut("execution(* get*())")   // Pointcut is the point where the advice is executed
    public void allGetters(){}

    @Pointcut("within(org.example.model.Circle)")
    public void allCircleMethods(){}



}


