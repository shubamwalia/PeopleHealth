package com.backend.api.aop;

import com.backend.api.helper.response.Success;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Aspect
@Component
public class SuccessResponse {

    @Around("com.backend.api.aop.PointcutExpression.resourceCreate()")
    public ResponseEntity<Success> successResponseCreating(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Object object = proceedingJoinPoint.proceed();

        Success success = new Success();
        success.setStatus(HttpStatus.CREATED.value());

        success.setPayload(new LinkedHashMap<String, Object>());
        success.getPayload().put("data", object);

        return new ResponseEntity<>(success, HttpStatus.CREATED);
    }

    @Around("com.backend.api.aop.PointcutExpression.successResponse() && !com.backend.api.aop.PointcutExpression.resourceCreate()")
    public ResponseEntity<Success> successResponseGeneric(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Object object = proceedingJoinPoint.proceed();

        Success success = new Success();
        success.setStatus(HttpStatus.OK.value());

        success.setPayload(new LinkedHashMap<String, Object>());
        success.getPayload().put("data", object);

        return new ResponseEntity<>(success, HttpStatus.OK);
    }

}
