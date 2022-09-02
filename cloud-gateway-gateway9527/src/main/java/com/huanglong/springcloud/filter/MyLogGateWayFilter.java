package com.huanglong.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
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

      @Autowired
    private NotAuthUrlProperties notAuthUrlProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

                String currentUrl = exchange.getRequest().getURI().getPath();
        System.out.println(currentUrl);
        //1:不需要认证的url
        if (shouldSkip(currentUrl)) {
            log.info("=====返回已跳过url{}===", currentUrl);
            return chain.filter(exchange);
        }



        log.info("***********come in MyLogGateWayFilter:  "+new Date());
/**
 * 这里设置请求路径要携带参数uname
 */    //http://localhost:9527/payment/lb?uname=33
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        List<String> list = exchange.getRequest().getHeaders().get("token");
        log.info("uname:{}",list);

        if(list == null)
        {
            log.info("*******用户名为null，非法用户，o(╥﹏╥)o");
            //给个回应
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
//            错在哪
            return exchange.getResponse().setComplete();
        }
//        log.info("*******用户名为nu，非法用户，o(╥﹏╥)o"+list);
        //合法用户去下一个过滤器
        return chain.filter(exchange);

    }

    @Override
    public int getOrder() {
        return 0;
    }

//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        log.info("***********come in MyLogGateWayFilter:  "+new Date());
///**
// * 这里设置请求路径要携带参数uname
// */
//        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
//        List<String> list = exchange.getRequest().getHeaders().get("token");
//        log.info("token:{}",list);
//
//        if(list == null)
//        {
//            log.info("*******用户名为null，非法用户，o(╥﹏╥)o");
//            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
//            return exchange.getResponse().setComplete();
//        }
////        log.info("*******用户名为nu，非法用户，o(╥﹏╥)o"+list);
//        return chain.filter(exchange);
//
//    }
//
//    @Override
//    public int getOrder() {
//        return 0;
//    }

        private boolean shouldSkip(String currentUrl) {
        PathMatcher pathMatcher = new AntPathMatcher();
        for (String skipPath : notAuthUrlProperties.getShouldSkipUrls()) {
            if (pathMatcher.match(skipPath, currentUrl)) {
                return true;
            }
        }
        return false;
    }
}
