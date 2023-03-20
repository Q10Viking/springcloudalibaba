package org.hzz;
import org.springframework.util.StringUtils;

import java.util.concurrent.Callable;
import java.util.stream.Stream;

public class ReduceTest {
    public static void main(String[] args) throws Exception {
        ReduceTest reduceTest = new ReduceTest();
        reduceTest.inCombinedTx(()-> {
            System.out.println("origin method run...");
            return null;
        }, new String[]{"wTransactionManager", "rTransactionManager"});
    }


    public <V> V m1(Callable<V> callable) throws Exception {
        System.out.println("wTransactionManager");
        return callable.call();
    }
    public <V> V m2(Callable<V> callable) throws Exception {
        System.out.println("rTransactionManager");
        return callable.call();
    }

    public <V> V inCombinedTx(Callable<V> callable, String[] transactions) throws Exception {
        // 相当于循环 [wTransactionManager,wTransactionManager]
        Callable<V> a = Stream.of(transactions)
                .filter(ele -> !StringUtils.isEmpty(ele))
                .distinct()
                .reduce(callable, (r, tx) -> {
                    switch (tx) {
                        case "wTransactionManager":
                            return () -> m1(r);
                        case "rTransactionManager":
                            return () -> m2(r);
                        default:
                            return r;
                    }
                },(r1,r2)->null); // 为了参数推断
        return a.call();
    }
}
/**
 * rTransactionManager
 * wTransactionManager
 * origin method run...
 */