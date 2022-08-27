package com.huanglong.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyselfRuleConfig {
    /**
     * 注意官方文档明确给出了警告：
     * 这个自定义配置类不能放在@ComponentScan所扫描的当前包下以及子包下，
     * 否则我们自定义的这个配置类就会被所有的Ribbon客户端所共享，达不到特殊化定制的目的了
     * （即不能放在启动类所在的包里因为@SpringBootApplication包含了@ComponentScan）
     */
    @Bean
    public IRule myRule()
    {
        return new RandomRule();//定义为随机
    }
}

