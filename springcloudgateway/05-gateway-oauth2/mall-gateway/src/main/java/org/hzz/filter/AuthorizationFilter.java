package org.hzz.filter;

import org.hzz.api.ResultCode;
import org.hzz.auth.TokenInfo;
import org.hzz.exception.GateWayException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Component
@Order(1)
public class AuthorizationFilter implements GlobalFilter, InitializingBean {
    private static Set<String> shouldSkipUrl = new LinkedHashSet<>();
    @Override
    public void afterPropertiesSet() throws Exception {
        // 不拦截认证的请求
        shouldSkipUrl.add("/oauth/token");
        shouldSkipUrl.add("/oauth/check_token");
        shouldSkipUrl.add("/user/getCurrentUser");
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        if(shouldSkip(path)){
            return chain.filter(exchange);
        }

        TokenInfo tokenInfo = (TokenInfo) exchange.getAttributes().get("tokenInfo");

        if(!tokenInfo.isActive()){
            throw new RuntimeException("token过期");
        }

        hasPremisson(tokenInfo,path);
        return chain.filter(exchange);
    }

    private boolean hasPremisson(TokenInfo tokenInfo,String currentUrl) {
        boolean hasPremisson = false;
        //登录用户的权限集合判断
        List<String> premessionList = Arrays.asList(tokenInfo.getAuthorities());
        PathMatcher pathMatcher = new AntPathMatcher();
        for (String url: premessionList) {
            if(pathMatcher.match(url,currentUrl)) {
                hasPremisson = true;
                break;
            }
        }
        if(!hasPremisson){
            throw new GateWayException(ResultCode.FORBIDDEN);
        }
        return hasPremisson;
    }

    private boolean shouldSkip(String requestPath){
        PathMatcher pathMatcher = new AntPathMatcher();
        for (String path:
             shouldSkipUrl) {
            if(pathMatcher.match(path,requestPath)){
                return true;
            }
        }
        return false;
    }
}
