package com.fzd.cloud;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

/**
 * 功能描述:
 *
 * @author: FZD
 * @date: 2020/2/21
 */
@RequestMapping("/movies")
@RestController
public class MovieController {

    private final Logger logger = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/users/{id}")
    @HystrixCommand(fallbackMethod = "getUserFallback")
    public User getUser(@PathVariable Long id){
        return restTemplate.getForObject("http://microservice-provider-user/users/{id}", User.class, id);
    }

    public User getUserFallback(Long id, Throwable e){
        logger.error("进入回退方法", e);
        return new User(id, "默认用户", "默认用户", 0, new BigDecimal(0));
    }

}
