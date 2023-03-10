package org.hzz.controller;

import lombok.extern.slf4j.Slf4j;
import org.hzz.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/findOrderByUserId/{id}")
    public R findOrderByUserId(@PathVariable("id") Integer id){
        log.info("Hello User method: "+id);
//        String orderUrl = "http://localhost:8020/order/findOrderByUserId/"+id;
        String orderUrl = "http://mall-order/order/findOrderByUserId/"+id;
        R r = restTemplate.getForObject(orderUrl, R.class);
        return r;
    }

}
