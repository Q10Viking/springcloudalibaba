package org.hzz.feign;

import org.hzz.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "mall-order",path = "/order")
public interface OrderFeignService {
    @GetMapping("/findOrderByUserId/{id}")
    R findOrderByUserId(@PathVariable("id") int id);
}
