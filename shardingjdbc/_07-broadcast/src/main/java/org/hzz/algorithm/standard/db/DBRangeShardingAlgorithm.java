package org.hzz.algorithm.standard.db;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Arrays;
import java.util.Collection;

/**
 * DB分片，BETWEEN
 * 自定义扩展的范围分片算法。实现对select * from course where cid between 2000 and 3000; 这类语句的数据源分片
 */
@Slf4j
public class DBRangeShardingAlgorithm implements RangeShardingAlgorithm<Long> {
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Long> shardingValue) {
        log.info("DB范围可用的数据源信息："+ Arrays.toString(availableTargetNames.toArray()));
        //实现按照 Between 进行范围分片。
        //例如 select * from course where cid between 2000 and 3000;
        Long lowerEndpoint = shardingValue.getValueRange().lowerEndpoint();//2000
        Long upperEndpoint = shardingValue.getValueRange().upperEndpoint();//3000
        //对于我们这个奇偶分离的场景，大部分范围查询都是要两张表都查。
        return availableTargetNames;
    }
}
