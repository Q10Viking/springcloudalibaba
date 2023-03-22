package org.hzz.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.hzz.lock.sql.MysqlDistributedLock;
import org.hzz.service.OrderCodeGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class LockApplicationTests {

	@Autowired
	private MysqlDistributedLock lock;

	@Autowired
	private OrderCodeGenerator orderCodeGenerator;

	private static volatile List<String> threadList = new ArrayList<>();
	private static int count = 5;

	@Test
	public void contextLoads() throws InterruptedException {
		log.info("Hello World");
		CountDownLatch countDownLatch = new CountDownLatch(count);
		for (int i = 0; i < count; i++) {
			new Thread(()->{
				try {
					countDownLatch.await();
					getOrderCode();
				} catch (InterruptedException e) {
				}
			}).start();
			countDownLatch.countDown();
		}

		while(threadList.size()<count){}
		threadList.forEach(System.out::println);
	
	}

	public void getOrderCode(){
		lock.lock();
		String genCode = orderCodeGenerator.genCode();
		log.info(Thread.currentThread().getName()+": "+genCode);
		threadList.add(Thread.currentThread().getName());
		lock.unLock();
	}

}
