package org.fullstack4.springmvc.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@Aspect
public class AoP {
    // 다른 메소드 실행 전 수행될 AOP 메소드
    @Before("within(org.fullstack4.springmvc..*)")
    public void beforeMethod() {
        log.info("--------------------------------------------메소드 시작 전");
    }

    // 다른 메소드 실행 후 수행될 AOP 메소드
    @After("within(org.fullstack4.springmvc..*)")
    public void afterMethod() {
        log.info("--------------------------------------------메소드 종료 후");
    }

    public void springAop(JoinPoint jpt) {
        Signature signature = jpt.getSignature();
        log.info("JoinPoint 정보출력");
        log.info("signature : " +  signature);
        log.info("signature.getName() : " +  signature.getName());
        log.info("--------------------------------------------");
    }
}
