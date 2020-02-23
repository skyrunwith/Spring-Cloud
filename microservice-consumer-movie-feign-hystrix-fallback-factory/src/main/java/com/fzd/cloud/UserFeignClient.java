package com.fzd.cloud;

import feign.Feign;
import feign.hystrix.FallbackFactory;
import feign.hystrix.HystrixFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "microservice-provider-user", fallbackFactory = UserFeignClientFallbackFactory.class
        , configuration = FeignEnableHystrixConfiguration.class)
public interface UserFeignClient {
    @GetMapping("/users/{id}")
    User getUser(@PathVariable Long id);
}

//获得造成fallback的原因
@Component
@Slf4j
class UserFeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {

    @Override
    public UserFeignClient create(Throwable cause) {
        return new UserFeignClient() {
            @Override
            public User getUser(Long id) {
                log.error("进入回退方法", cause);
                return new User(id, "默认用户", "默认用户", 0, new BigDecimal(0));
            }
        };
    }
}

//局部启用
class FeignEnableHystrixConfiguration {
    @Bean
    @Scope(value = "prototype")
    public HystrixFeign.Builder builder() {
        return new HystrixFeign.Builder();
    }
}
//局部禁用
class FeignDisableHystrixConfiguration {
    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder() {
        return Feign.builder();
    }
}