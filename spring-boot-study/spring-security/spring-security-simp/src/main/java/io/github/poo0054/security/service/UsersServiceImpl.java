package io.github.poo0054.security.service;

import io.github.poo0054.security.mapper.UsersMapper;
import io.github.poo0054.security.po.Role;
import io.github.poo0054.security.po.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;


public class UsersServiceImpl implements UserDetailsService {

    @Lazy
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = getUsers(username);
        if (users == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<Role> roles = usersMapper.getRole(users.getId());
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }
        users.setAuthorities(authorities);
        return users;
    }


    public Users getUsers(String username) {
        return usersMapper.selectUserByUsername(username);
    }
}
