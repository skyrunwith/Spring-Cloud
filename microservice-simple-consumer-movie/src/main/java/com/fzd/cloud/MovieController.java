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
    private RestTemplate restTemplate;

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id){
        return restTemplate.getForObject("http://localhost:8000/users/{id}", User.class, id);
    }

    /**
     * 测试微服务访问timeout异常
     * @return
     */
    @GetMapping("/timeout")
    public User timeout(){
        return restTemplate.getForObject("http://localhost:8000/users/timeout", User.class);
    }
}
