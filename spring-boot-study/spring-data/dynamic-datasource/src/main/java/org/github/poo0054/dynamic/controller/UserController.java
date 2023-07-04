package org.github.poo0054.dynamic.controller;

import org.github.poo0054.dynamic.po.User;
import org.github.poo0054.dynamic.service.Service01;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhangzhi
 */
@RestController
public class UserController {
    @Autowired
    Service01 queryForObject;

    @GetMapping
    public void test() {
        List<User> user = queryForObject.user();
        System.out.println(user);
    }
}
