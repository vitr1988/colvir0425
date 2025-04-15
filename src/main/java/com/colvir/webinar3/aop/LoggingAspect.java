package com.colvir.webinar3.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(public void com.colvir.webinar3.EmployeeService.print*())")
    public void printPatternMethod() {
    }

    @Before("printPatternMethod()")
    public void printPatternLogging() {
        System.out.println("Вызван метод print*");
    }

    @Before("printPatternMethod()")
    public void printPatternLogging2() {
        System.out.println("Повторное напоминание: вызван метод print*");
    }

    @Before("execution(public void com.colvir.webinar3.service.ClientService.print*())")
    public void printPatternForClientService() {
        System.out.println("Вызван printAll для ClientService");
    }

    @Before("execution(* *())")
    public void printAllEmptyMethodsCall() {
        System.out.println("Вызван метод без аргументов");
    }

    @Before("execution(* *(String, String))")
    public void printTwoInputStringsCall() {
        System.out.println("Вызван метод, который на входе принимает две строки");
    }

    @Before("execution(* *(..))")
    public void printAllMethodsCall() {
        System.out.println("Вызывается любой метод");
    }
}
