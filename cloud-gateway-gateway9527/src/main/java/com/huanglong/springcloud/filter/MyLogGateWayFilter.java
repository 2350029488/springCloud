package com.huanglong.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;

/**
 * 过滤器的自定义配置
 *
 * 这里可以做权限认证？ security?
 */
@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("***********come in MyLogGateWayFilter:  "+new Date());
/**
 * 这里设置请求路径要携带参数uname
 */
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        List<String> list = exchange.getRequest().getHeaders().get("token");

        if(uname == null)
        {
            log.info("*******用户名为null，非法用户，o(╥﹏╥)o");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        log.info("*******用户名为nu，非法用户，o(╥﹏╥)o"+list);
        return chain.filter(exchange);

    }

    @Override
    public int getOrder() {
        return 0;
    }
}
