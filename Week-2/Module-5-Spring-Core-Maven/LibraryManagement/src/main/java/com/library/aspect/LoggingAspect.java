package com.library.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;

@Aspect
public class LoggingAspect {

    // Exercise 3 & 8: Before advice
    @Before("execution(* com.library.service.BookService.*(..))")
    public void logBefore() {
        System.out.println(" [BEFORE] Method is about to execute");
    }

    // Exercise 3 & 8: After advice
    @After("execution(* com.library.service.BookService.*(..))")
    public void logAfter() {
        System.out.println(" [AFTER] Method finished executing");
    }

    // Exercise 3 & 8: Around advice — measures time
    @Around("execution(* com.library.service.BookService.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint)
            throws Throwable {

        long start = System.currentTimeMillis();

        System.out.println(" [AROUND-START] "
            + joinPoint.getSignature().getName() + " started");

        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();

        System.out.println(" [AROUND-END] "
            + joinPoint.getSignature().getName()
            + " finished in " + (end - start) + "ms");

        return result;
    }
}