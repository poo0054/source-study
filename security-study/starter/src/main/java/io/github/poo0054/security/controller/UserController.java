package io.github.poo0054.security.controller;

import io.github.poo0054.security.po.Users;
import io.github.poo0054.security.service.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author poo00
 */
@RestController
public class UserController {

    @Autowired
    private UsersServiceImpl userInfoService;

    @GetMapping("/get")
//	public Users getUser(String username,@CsrfToken CsrfToken token, @AuthenticationPrincipal Users customUser, @CurrentSecurityContext Authentication authentication) {
    public Users getUser(String username, @AuthenticationPrincipal Users customUser, @CurrentSecurityContext SecurityContext securityContext) {
        return userInfoService.getUsers(username);
    }

    @GetMapping("/getUser")
    @PreAuthorize("hasRole('sys')") // 只能user角色才能访问该方法
//	@PreAuthorize("hasAuthority('menu:sys')") // 只能user角色才能访问该方法
//	@PreAuthorize("hasAnyRole('user')") // 只能user角色才能访问该方法
    public Users getUser(@PathVariable @RequestBody String username) {
        return userInfoService.getUsers(username);
    }


}
