package com.fzd.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 功能描述:
 *
 * @author: FZD
 * @date: 2020/2/21
 */
@RequestMapping("/movies")
@RestController
public class MovieController {

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id){
        return userFeignClient.getUser(id);
    }

    /**
     * 测试微服务访问timeout异常
     * @return
     */
    @GetMapping("/timeout")
    public User timeout(){
        return userFeignClient.testTimeout();
    }
}
