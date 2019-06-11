package com.backend.api.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

    private final Logger logger = LogManager.getLogger(LoggerAspect.class.getName());

    @Before("com.backend.api.aop.PointcutExpression.methodsWithExceptionHandler()")
    public void logger(JoinPoint joinPoint){

        MethodSignature methodSignature =  (MethodSignature)joinPoint.getSignature();
        Object[] objects = joinPoint.getArgs();

        logger.info("Method Signature : " + methodSignature);

        logger.info("---- Method arguments ----");
        for (Object arg : objects){
            logger.info(arg);
        }
    }

}
