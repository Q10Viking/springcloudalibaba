package org.hzz.spiextension;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.spi.keygen.ShardingKeyGenerator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class MyKeyGenerator implements ShardingKeyGenerator {

    private Properties properties = new Properties();
    private AtomicLong atomicLong = new AtomicLong(0L);

    @Override
    public Comparable<?> generateKey() {
        LocalDateTime now = LocalDateTime.now();
        String time = DateTimeFormatter.ofPattern("HHmmssSSS").format(now);
        Long key = Long.parseLong(time + atomicLong.getAndIncrement());
        log.info("生成Key: "+key);
        return key;
    }

    @Override
    public String getType() {
        return "MYKEY";
    }

    @Override
    public Properties getProperties() {
        return this.properties;
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
