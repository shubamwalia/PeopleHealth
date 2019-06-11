package com.backend.api.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointcutExpression {

    @Pointcut("within(com.backend.api.controller..*)")
    public void successResponse() {}

    @Pointcut("execution(* com.backend.api.*.*.*(..))")
    public void methodsWithExceptionHandler() {}

    @Pointcut("within(com.backend.api.exceptionhandler..*)")
    public void methodsWithoutExceptionHandler(){}

    @Pointcut("execution(* com.backend.api.controller.*.*add*(..))")
    public void resourceCreate(){}

    @Pointcut("execution(* com.backend.api.controller.*.*get*(..))")
    public void resourceRetrieve(){}
}
