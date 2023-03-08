package org.hzz.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;

@Component
public class CheckAuthFilter implements GlobalFilter, Ordered {
    private static final Logger log = LoggerFactory.getLogger(CheckAuthFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String token = exchange.getRequest().getHeaders().getFirst("token");
        if(token == null){
            log.info("tocken为空");
            ServerHttpResponse response = exchange.getResponse();
            response.getHeaders().add("Content-Type",
                    "application/json;charset=UTF-8");
            // 401 用户没有访问权限
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            byte[] bytes = HttpStatus.UNAUTHORIZED.getReasonPhrase().getBytes();
            DataBuffer buffer = response.bufferFactory().wrap(bytes);
            // 请求结束，不继续向下请求
            return response.writeWith(Mono.just(buffer));
        }
        log.info("认证校验");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
