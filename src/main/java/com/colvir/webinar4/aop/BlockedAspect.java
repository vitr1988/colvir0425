package com.colvir.webinar4.aop;

import com.colvir.webinar4.annotation.Blocked;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class BlockedAspect {

    @Around("@annotation(com.colvir.webinar4.annotation.Blocked)")
//    @Around("@annotation(blocked)")
//    public Object invoke(ProceedingJoinPoint joinPoint, Blocked blocked) throws Throwable {
    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Method name: " + joinPoint.getSignature().getName());
        System.out.println("Method args: " + Arrays.toString(joinPoint.getArgs()));
//        return joinPoint.proceed();
//        return null;
        throw new IllegalArgumentException("Method should not be executed");
//                blocked.value().isEmpty()
//                        ? "Method should not be executed"
//                        : blocked.value()
//        );
    }
}
