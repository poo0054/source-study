package org.github.rest.controller;

import org.github.rest.po.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangzhi
 */
@RestController
public class TestController {

    @GetMapping("/variables/{test}")
    public String variables(@PathVariable("test") String test) {
        return test;
    }

    @GetMapping("/parameter")
    public String parameter(@RequestParam("name") String name, @RequestParam("id") Integer id) {
        return id + "--" + name;
    }

    @GetMapping("/user")
    public User user(User user) {
        return user;
    }

    @PostMapping("/postUser")
    public User postUser(@RequestBody User user) {
        return user;
    }


    @GetMapping("/head")
    public String head(HttpServletRequest httpServletRequest) {
        return "hi i get httpServletRequest";
    }
}
