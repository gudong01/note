package annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect         //定义一个切面类
public class AopAnnotation {
    @Before("execution(* service.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("before");
    }
    @After(("execution(* service.UserServiceImpl.*(..))"))
    public void after(){
        System.out.println("after");
    }

    @Around(("execution(* service.UserServiceImpl.*(..))"))
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around before");
        Object o = joinPoint.proceed();
        System.out.println("around after");
        return o;
    }
    @AfterReturning("execution(* service.UserServiceImpl.*(..))")
    public void afterReturning(){
        System.out.println("afterReturning");
    }
}
