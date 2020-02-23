package com.fzd.cloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "microservice-provider-user", fallback = UserFeignClientFallback.class)
public interface UserFeignClient {
    @GetMapping("/users/{id}")
    User getUser(@PathVariable Long id);
}

//服务降级
@Component
class UserFeignClientFallback implements UserFeignClient {
    @Override
    public User getUser(Long id) {
        return new User(id, "默认用户", "默认用户", 0, new BigDecimal(0));
    }
}