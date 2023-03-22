package org.hzz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    
    @GetMapping("/test")
    public Object helloWorld(){
        System.out.println("HelloController.helloWorld()");
        return "Hello World";
    }

}
