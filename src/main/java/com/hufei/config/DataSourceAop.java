package com.hufei.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author:hufei
 * @CreateTime:2020-11-16
 * @Description:设置数据源路由key，数据源AOP方式：默认情况下，所有的查询都走从库，插入/修改/删除走主库
 * 自定义实现多数据源 - 7
 */
@Aspect
@Component
public class DataSourceAop {
    @Pointcut("!@annotation(com.hufei.config.annotation.Master) " +
            "&& (execution(* com.hufei.*.service..*.select*(..)) " +
            "|| execution(* com.hufei.*.service..*.get*(..))" +
            "|| execution(* com.hufei.*.service..*.find*(..)))")
    public void readPointcut() {
    }

    @Pointcut("@annotation(com.hufei.config.annotation.Master) " +
            "|| execution(* com.hufei.*.service..*.insert*(..)) " +
            "|| execution(* com.hufei.*.service..*.add*(..)) " +
            "|| execution(* com.hufei.*.service..*.update*(..)) " +
            "|| execution(* com.hufei.*.service..*.edit*(..)) " +
            "|| execution(* com.hufei.*.service..*.delete*(..)) " +
            "|| execution(* com.hufei.*.service..*.remove*(..))" +
            "|| execution(* com.hufei.*.service..*.save*(..))")
    public void writePointcut() {
    }

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }

    /**
     * 另一种写法：if...else...  判断哪些需要读从数据库，其余的走主数据库
     */
//    @Before("execution(* com.cjs.example.service.impl.*.*(..))")
//    public void before(JoinPoint jp) {
//        String methodName = jp.getSignature().getName();
//
//        if (StringUtils.startsWithAny(methodName, "get", "select", "find")) {
//            DBContextHolder.slave();
//        }else {
//            DBContextHolder.master();
//        }
//    }
}
