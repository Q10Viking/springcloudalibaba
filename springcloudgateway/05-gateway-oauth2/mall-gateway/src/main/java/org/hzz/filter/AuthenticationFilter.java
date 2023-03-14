package org.hzz.filter;

import org.hzz.auth.MDA;
import org.hzz.auth.TokenInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 认证过滤器
 */
@Component
@Order(0)
public class AuthenticationFilter implements GlobalFilter, InitializingBean {
    private static Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Autowired
    private RestTemplate restTemplate;
    private static Set<String> shouldSkipUrl = new LinkedHashSet<>();
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        // 不需要认证的url
        if(shouldSkipUrl.contains(path)){
            log.info("不需要认证的"+path);
            return chain.filter(exchange);
        }

        String authorization = exchange.getRequest().getHeaders().getFirst("Authorization");
        // 请求头为空
        if(StringUtils.isEmpty(authorization)){
            throw new RuntimeException("请求头为空");
        }

        TokenInfo tokenInfo = null;
        try {
            tokenInfo = getTokenInfo(authorization);
        }catch (Exception e){
            throw new RuntimeException("校验token异常");
        }

        // 将解析的token向下游传递
        exchange.getAttributes().put("tokenInfo",tokenInfo);
        return chain.filter(exchange);
    }

    private TokenInfo getTokenInfo(String authorization){
        String token = org.apache.commons.lang.StringUtils.substringAfter(authorization, "bearer ");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.setBasicAuth(MDA.clientId,MDA.clientSecret);

        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("token",token);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, httpHeaders);
        ResponseEntity<TokenInfo> response = restTemplate.exchange(MDA.checkTokenUrl, HttpMethod.POST, httpEntity, TokenInfo.class);
        TokenInfo tokenInfo = response.getBody();
        return tokenInfo;
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        // 不拦截认证的请求
        shouldSkipUrl.add("/oauth/token");
        shouldSkipUrl.add("/oauth/check_token");
        shouldSkipUrl.add("/user/getCurrentUser");
    }
}
