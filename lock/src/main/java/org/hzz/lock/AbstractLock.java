package org.hzz.lock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractLock implements Lock{

    @Override
    public void lock() {
        if(tryLock()){
            log.info("获取锁成功");
        }else{
            waitLock();
            this.lock();
        }
    }

    public abstract boolean tryLock();
    public abstract void waitLock();        
}
