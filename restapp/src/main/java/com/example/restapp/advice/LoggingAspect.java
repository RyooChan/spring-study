package com.example.restapp.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component      // 서버가 로딩할 때 이 컴포넌트가 같이 로딩된다. 즉 서버가 start될 때 같이 적용된다.
public class LoggingAspect {

    @Around("execution(* com.example.restapp.controller.UserController.*(..))")
    public Object setLog(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("실행 시작: "
                + pjp.getSignature().getDeclaringTypeName() + "."
                + pjp.getSignature().getName());
        long startMillis = System.currentTimeMillis();
        Object result = pjp.proceed();
        long executionMillis = System.currentTimeMillis() - startMillis;
        System.out.println("실행 완료: " + executionMillis + "밀리초 소요됨 :"
                + pjp.getSignature().getDeclaringTypeName() + "."
                + pjp.getSignature().getName());

        return result;
    }


    @Before("execution(* com.example.restapp.service.*.get*(..))")      // 어떤 메소드 실행 전에 반드시 이 함수가 실행된다. -> 모든 service의 get메소드가 실행되기 전에
    public void logger() {
        System.out.println("logger test before service methods");
    }
}