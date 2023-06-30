package org.github.poo0054.flow.controller;

import org.github.poo0054.flow.dao.UserPaging;
import org.github.poo0054.flow.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * @author zhangzhi
 */
@RestController
public class UserController {

    @Autowired
    UserPaging userPaging;

    @GetMapping("userbyid")
    public Mono<User> userById(Long id) {
        return Mono.justOrEmpty(Objects.requireNonNull(userPaging.findById(id).orElse(null)));
    }

    @GetMapping("user")
    public Flux<Page<User>> getUser() {
        return Flux.just(userPaging.findAll(Pageable.ofSize(5)));
    }

}
