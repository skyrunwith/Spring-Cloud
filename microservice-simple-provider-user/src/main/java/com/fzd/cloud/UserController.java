package com.fzd.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

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

    /**
     * 测试微服务访问timeout异常
     * @return
     */
    @GetMapping(value = "/timeout")
    public Optional<User> testTimeout() throws InterruptedException {
        Thread.sleep(TimeUnit.MINUTES.toMillis(3));
        return this.userRepository.findById(1L);
    }

    @GetMapping(value = "/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return this.userRepository.findById(id);
    }

}
