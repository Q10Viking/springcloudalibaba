package org.hzz.algorithm.standard.table;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;

/**
 * Table精确分片
 */
@Slf4j
public class TablePreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        // Table精确可用的数据源信息：[course_1, course_2]
        log.info("Table精确可用的数据源信息："+ Arrays.toString(availableTargetNames.toArray()));
        BigInteger cid = BigInteger.valueOf(shardingValue.getValue());
        log.info("表cid: " + cid);
        // course_$->{(cid%4).intdiv(2)+1}
        BigInteger v = cid.mod(new BigInteger("4")).divide(new BigInteger("2")).add(new BigInteger("1"));
        String key = shardingValue.getLogicTableName()+"_"+v;
        // 如：Table分片后的表：course_1
        log.info("Table分片后的表："+key);
        if(availableTargetNames.contains(key)){
            return key;
        }
        throw new UnsupportedOperationException(" route "+key+" is not supported. please check your config");
    }
}
