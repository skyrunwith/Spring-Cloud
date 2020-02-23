package com.fzd.cloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.simple.SimpleDiscoveryProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * 功能描述:
 *
 * @author: FZD
 * @date: 2020/2/21
 */
@RequestMapping(value = "/users")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    SimpleDiscoveryProperties discoveryProperties;
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping(value = "/{id}")
    public Optional<User> getUserById(@PathVariable Long id){
        ServiceInstance serviceInstance = discoveryProperties.getLocal();

        ServiceInstance instance = discoveryProperties.getLocal();

        logger.info("/hello, host: " + instance.getHost() + ", service_id: " + instance.getServiceId());
        return this.userRepository.findById(id);
    }

}
