package io.github.poo0054.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangzhi
 */
@RestController
public class TestController {


    @GetMapping("/test")
    @PreAuthorize("hasRole('sys')")
    public String test() {
        return "hello";
    }

}
