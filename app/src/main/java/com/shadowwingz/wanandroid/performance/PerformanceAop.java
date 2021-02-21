package com.shadowwingz.wanandroid.performance;

/**
 * 使用 AspectJ 检测函数耗时
 */
//@Aspect
//public class PerformanceAop {

//    @Pointcut("execution(* com.shadowwingz.wanandroid.ui.home.HomeFragment.**(..))")
//    public void callMethod() {
//    }
//
//    @Around("callMethod()")
//    public void beforeMethodCall(ProceedingJoinPoint joinPoint) {
//        String name = joinPoint.getThis() + "#" + joinPoint.getSignature().getName();
//        long time = System.currentTimeMillis();
//        try {
//            joinPoint.proceed();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//        LogUtil.d(name + " cost " + (System.currentTimeMillis() - time));
//    }
//}
