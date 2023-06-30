package com.codicefun.blog.dao;

import com.codicefun.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 根据用户名和密码查找用户
     *
     * @param username 用户名
     * @param password 密码
     * @return 查询结果
     */
    User findByUsernameAndPassword(String username, String password);

}
