package org.hzz.controller;

import lombok.extern.slf4j.Slf4j;
import org.hzz.service.OrderFeignService;
import org.hzz.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private OrderFeignService orderFeignService;

    @GetMapping("/findOrderByUserId/{id}")
    public R findOrderByUserId(@PathVariable("id") Integer id){
        log.info("获取用户订单");
       return orderFeignService.findOrderByUserId(id);

    }

}
