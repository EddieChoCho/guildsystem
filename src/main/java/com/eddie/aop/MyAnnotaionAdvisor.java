package com.eddie.aop;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyAnnotaionAdvisor extends AbstractPointcutAdvisor {

    private final Pointcut pointcut;
    private final MyInterceptor interceptor;

    @Autowired
    public MyAnnotaionAdvisor(MyPointcut pointcut, MyInterceptor interceptor){
        this.pointcut = pointcut;
        this.interceptor = interceptor;
    }




    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return interceptor;
    }
}
