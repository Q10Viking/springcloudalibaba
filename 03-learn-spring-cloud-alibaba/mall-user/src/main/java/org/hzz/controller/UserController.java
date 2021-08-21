package org.hzz.controller;

import org.hzz.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;


    @GetMapping("/findOrderByUserId/{id}")
    public R findOrderByUserId(@PathVariable("id") Integer id){
//        String orderUrl = "http://localhost:8020/order/findOrderByUserId/"+id;
        String orderUrl = getUrl("mall-order")+"/order/findOrderByUserId/"+id;
        R r = restTemplate.getForObject(orderUrl, R.class);
        return r;
    }

    private final AtomicInteger count = new AtomicInteger(0);
    private String getUrl(String serviceName){
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
        // 通过负载均衡获取到其中的一个实例
        ServiceInstance serviceInstance = instances.get(getIndex(instances.size()));
        URI uri = serviceInstance.getUri();
        return uri.toString();
    }
    // CAS获取到洗衣歌实例的索引位置
    private int getIndex(int size){
        for(;;){
            int currentIndex = count.get();
            int next = (currentIndex+1)%size;
            if(count.compareAndSet(currentIndex,next)){
                return currentIndex;
            }
        }
    }
}
