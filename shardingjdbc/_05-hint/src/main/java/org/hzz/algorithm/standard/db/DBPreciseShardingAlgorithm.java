package org.hzz.algorithm.standard.db;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;

/**
 * DB的一个分片策略 单条件或者IN里面的数据
 */
@Slf4j
public class DBPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

    /**
     * @param availableTargetNames 有效的数据源或表的名字。这里就对应配置文件中配置的数据源信息
     * @param shardingValue 包含 逻辑表名、分片列和分片列的值。
     * @return 返回目标结果
     */
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        // DB精确可用的数据源信息：[m1, m2]
        log.info("DB精确可用的数据源信息："+ Arrays.toString(availableTargetNames.toArray()));
        BigInteger cid = BigInteger.valueOf(shardingValue.getValue());
        log.info("数据库cid: " + cid);
        // m$->{cid%2+1}
        BigInteger val = cid.mod(new BigInteger("2")).add(new BigInteger("1"));
        String key = "m"+val;
        // 如：DB分片后的库：m1，DB分片后的库：m2
        log.info("DB分片后的库："+key);
        if(availableTargetNames.contains(key)){
            return key;
        }
        throw new UnsupportedOperationException(" route "+key+" is not supported. please check your config");
    }
}
