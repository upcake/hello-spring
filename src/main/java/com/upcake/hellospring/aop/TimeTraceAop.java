package com.upcake.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//AOP로 사용할 때 붙는 어노테이션
@Aspect
//component-scan으로 사용해도 되나 config에 등록해서 사용하는 것을 더 선호한다
@Component
public class TimeTraceAop {

    //실행 대상 등록 : 모든 패키지에 적용한다
    //* 패키지명..밑에있는거클래스명등등에게(파라미터타입)
    @Around("execution(* com.upcake.hellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
