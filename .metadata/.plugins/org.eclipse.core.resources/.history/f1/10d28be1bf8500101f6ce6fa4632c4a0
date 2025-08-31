package com.wipro.mohan.aop;


import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class EndpointLoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(EndpointLoggingAspect.class);

    // Pointcut: Any method inside a class annotated with @RestController
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void anyRestController() {}

    @Around("anyRestController()")
    public Object logEndpoint(ProceedingJoinPoint pjp) throws Throwable {
        // Get HTTP request details
        ServletRequestAttributes attrs =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = (attrs != null) ? attrs.getRequest() : null;
        String httpMethod = (request != null) ? request.getMethod() : "?";
        String uri = (request != null) ? request.getRequestURI() : "?";

        // Get method name
        MethodSignature sig = (MethodSignature) pjp.getSignature();
        String methodName = sig.getMethod().getName();

        // BEFORE
        log.info("Incoming {} request for [{}] via controller method: {}", httpMethod, uri, methodName);

        long start = System.currentTimeMillis();
        try {
            Object result = pjp.proceed(); // Execute controller method
            return result;
        } finally {
            long ms = System.currentTimeMillis() - start;
            // AFTER
            log.info("Completed {} {} in {} ms", httpMethod, uri, ms);
        }
    }
}

