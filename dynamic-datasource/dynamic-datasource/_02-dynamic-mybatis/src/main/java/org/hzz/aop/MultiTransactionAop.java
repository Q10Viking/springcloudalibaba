package org.hzz.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hzz.transaction.ComboTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MultiTransactionAop {
    @Autowired
    private ComboTransaction comboTransaction;


    @Pointcut("within(org.hzz.service.*)")
    public void pointCut() {
    }

    @Around("pointCut() && @annotation(multiTransactional)")
    public Object inMultiTransactions(ProceedingJoinPoint pjp, MultiTransactional multiTransactional) {
        return comboTransaction.inCombinedTx(() -> {
            try {
                return pjp.proceed();       //执行目标方法
            } catch (Throwable throwable) {
                if (throwable instanceof RuntimeException) {
                    throw (RuntimeException) throwable;
                }
                throw new RuntimeException(throwable);
            }
        }, multiTransactional.value());
    }
}
