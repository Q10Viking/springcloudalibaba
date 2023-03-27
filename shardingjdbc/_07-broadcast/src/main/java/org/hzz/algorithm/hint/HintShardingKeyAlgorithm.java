package org.hzz.algorithm.hint;

import com.alibaba.druid.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


/**
 * 库和表公用的分片策略
 */
@Slf4j
public class HintShardingKeyAlgorithm implements HintShardingAlgorithm<Long> {
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<Long> shardingValue) {
        // 数据库：availableTargetNames: [m1, m2],表：availableTargetNames: [course_1, course_2]
        log.info("availableTargetNames: "+ Arrays.toString(availableTargetNames.toArray()));
        Collection<Long> values = shardingValue.getValues();
        // hintManager.addDatabaseShardingValue addTableShardingValue传的对应的值
        log.info("values: "+ Arrays.toString(values.toArray()));

        List<String> results = new ArrayList<>();
        for (String target: availableTargetNames){
            String suffix = target.substring(target.length()-1);
            if(StringUtils.isNumber(suffix)){
                for (Long v: values){
                    // course_$->{(cid%4).intdiv(2)+1}
                    int r = BigInteger.valueOf(v).mod(new BigInteger("4")).divide(new BigInteger("2")).add(new BigInteger("1")).intValue();
                    if(r == Integer.parseInt(suffix)){
                        results.add(target);
                    }
                }
            }
        }
        log.info("results: "+Arrays.toString(results.toArray()));
        return results;
    }
}
