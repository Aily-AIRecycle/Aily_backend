package aily.server.aop;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Aspect
@Component
public class Logere {

    @Around("execution(* aily.server.controller.*Controller.*(..))")
    public Object AroundAdviceWatch(ProceedingJoinPoint jp) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ipAddress = request.getRemoteAddr();

        StopWatch watch = new StopWatch();

        watch.start();
        Object result = jp.proceed();
        watch.stop();

        long time = watch.getTotalTimeMillis();
        MethodSignature sig = (MethodSignature)  jp.getSignature();
        System.out.println(sig.getName() + ", " + time + "ms걸림");
        System.out.println("ip = " + ipAddress);
        return result;
    }

}
