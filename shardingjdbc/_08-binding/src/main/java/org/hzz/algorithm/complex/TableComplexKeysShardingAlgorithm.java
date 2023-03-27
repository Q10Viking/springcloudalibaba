package org.hzz.algorithm.complex;

import com.google.common.collect.Range;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 表的复杂设定
 */
@Slf4j
public class TableComplexKeysShardingAlgorithm implements ComplexKeysShardingAlgorithm<Long> {

    /**
     *
     * @param availableTargetNames 目标数据源 或者 表 的值。
     * @param shardingValue logicTableName逻辑表名 columnNameAndShardingValuesMap 分片列的精确值集合。 columnNameAndRangeValuesMap 分片列的范围值集合
     * @return
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<Long> shardingValue) {
        //实现按照 Between 进行范围分片。
        //例如 select * from course where cid in (1,3,5) and userid Between 200 and 300;

        // avaialbeTargetNames: [course_1, course_2]
        log.info("avaialbeTargetNames: "+ Arrays.toString(availableTargetNames.toArray()));
        Collection<Long> cids = shardingValue.getColumnNameAndShardingValuesMap().get("cid");
        Range<Long> userIdRange = shardingValue.getColumnNameAndRangeValuesMap().get("user_id");

        Long lowerEndPoint = userIdRange.lowerEndpoint();
        Long upperEndPoint = userIdRange.upperEndpoint();
        // lowerEndPoint: 99 upperEndPoint: 100
        log.info("lowerEndPoint: "+lowerEndPoint+" upperEndPoint: "+upperEndPoint);

        List<String> results = new ArrayList<>();
        //实现自定义分片逻辑 例如可以自己实现 course_$->{cid%2}_${user%id} 这样的复杂分片逻辑
        for (Long cid: cids){
            // 为了简化，这里只实现course_$->{(cid%4).intdiv(2)+1}
            BigInteger c = BigInteger.valueOf(cid);
            BigInteger v = c.mod(new BigInteger("4")).divide(new BigInteger("2")).add(new BigInteger("1"));
            String key = shardingValue.getLogicTableName()+"_"+ v;
            results.add(key);
        }
        // results: [course_1, course_1]
        log.info("results: "+Arrays.toString(results.toArray()));
        return results;
    }


}
