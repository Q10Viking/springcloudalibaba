package org.hzz.controller;

import lombok.extern.slf4j.Slf4j;
import org.hzz.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.port}")
    private int port;

    @GetMapping("/findOrderByUserId/{id}")
    public R findOrderByUserId(@PathVariable("id") Integer id){
        log.info("current mall-order service port: {}",port);
        return R.ok("maller-order"+ " port: "+ port + " id: "+id);
    }


    @GetMapping("/testgateway")
    public R testgateway(HttpServletRequest request){
        log.info("gateWay获取请求头X-Request-Color："
                +request.getHeader("X-Request-Color"));
        return R.ok("Success: X-Request-Color = "+request.getHeader("X-Request-Color"));
    }

    @GetMapping("/testgateway2")
    public R testGateway2(@RequestHeader("X-Request-color") String color) throws Exception {
        log.info("gateWay获取请求头X-Request-Color："+color);
        return R.ok("Success: X-Request-Color = "+color);
    }

    @GetMapping("/testgateway3")
    public R testGateway3(@RequestParam("book") String book) throws Exception {
        log.info("gateWay获取请求参数color:"+book);
        return R.ok("Success: "+book);
    }
}
