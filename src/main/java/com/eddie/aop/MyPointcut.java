package com.eddie.aop;

import com.eddie.annotation.MyAnnotation;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.annotation.AnnotationClassFilter;
import org.springframework.aop.support.annotation.AnnotationMethodMatcher;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class MyPointcut implements Pointcut {

    private final ClassFilter classFilter = new AnnotationClassFilter(Service.class);
    private final MethodMatcher methodMatcher = new AnnotationMethodMatcher(MyAnnotation.class);

    @Override
    public ClassFilter getClassFilter() {
        return classFilter;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }
}
