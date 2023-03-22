package org.hzz.lock.sql;

import org.hzz.lock.AbstractLock;
import org.hzz.mapper.MethodlockMapper;
import org.hzz.model.Methodlock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MysqlDistributedLock extends AbstractLock{

    @Autowired
    private MethodlockMapper methodlockMapper;

    @Override
    public void unLock() {
        methodlockMapper.deleteByMethodlock("lock");
    }

    @Override
    public boolean tryLock() {
        try{
            methodlockMapper.insertSelective(new Methodlock("lock"));
        }catch(Exception e){
            log.info("获取锁失败");
            return false;
        }
        return true;
    }

    @Override
    public void waitLock() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
