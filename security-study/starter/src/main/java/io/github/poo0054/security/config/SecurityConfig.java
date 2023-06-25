package io.github.poo0054.security.config;

import io.github.poo0054.security.service.AccessDecisionVoterImpl;
import io.github.poo0054.security.service.UsersServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author poo00
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 配置认证
        http.formLogin();

        // 设置URL的授权问题
        // 多个条件取交集
        http.authorizeRequests()
                // 匹配 / 控制器  permitAll() 不需要被认证就可以访问
                .antMatchers("/login").permitAll()
                .antMatchers("/error").permitAll()
                .antMatchers("/fail").permitAll()
                // anyRequest() 所有请求   authenticated() 必须被认证
                .anyRequest().authenticated()
                .accessDecisionManager(accessDecisionManager());
        // 关闭csrf
        http.csrf();

        http.userDetailsService(userDetailsService());
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<?>> voters = new ArrayList<>();
        voters.add(accessDecisionVoter());
        voters.add(roleVoter());
        return new AffirmativeBased(voters);
    }

    @Bean
    public UsersServiceImpl userDetailsService() {
        return new UsersServiceImpl();
    }

    @Bean
    public AccessDecisionVoter accessDecisionVoter() {
        return new AccessDecisionVoterImpl();
    }

    @Bean
    public AccessDecisionVoter roleVoter() {
        return new RoleVoter();
    }
}
