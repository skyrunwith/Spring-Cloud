package com.fzd.cloud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 功能描述:
 *
 * @author: FZD
 * @date: 2020/2/21
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
