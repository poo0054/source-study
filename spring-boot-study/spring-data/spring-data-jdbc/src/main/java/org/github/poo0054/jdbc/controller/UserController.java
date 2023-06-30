package org.github.poo0054.jdbc.controller;

import org.github.poo0054.jdbc.dao.UserPaging;
import org.github.poo0054.jdbc.dao.UserRepository;
import org.github.poo0054.jdbc.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author zhangzhi
 */
@RestController
public class UserController {
    @Autowired
    UserPaging userPaging;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JdbcAggregateTemplate jdbcAggregateOperations;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("userbyid")
    public User userById(Long id) {
        Optional<User> byId = userPaging.findById(id);
        System.out.println(byId);
        return byId.orElse(null);
    }

    @GetMapping("user")
    public Page<User> getUser() {
        Page<User> pagingAll = userPaging.findAll(Pageable.ofSize(5));
        System.out.println(pagingAll);
        return pagingAll;
    }

}
