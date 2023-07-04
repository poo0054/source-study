package org.github.poo0054.dynamic.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.github.poo0054.dynamic.po.User;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhangzhi
 */
@Service
public class Service01 {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //    @DSTransactional
    @DS("master")
    @Transactional
    public List<User> user() {
        Service01 service01 = (Service01) AopContext.currentProxy();
        jdbcTemplate.execute("insert into user values (1,'张三')");
        List<User> user = service01.user01();
        System.out.println(user);
        return jdbcTemplate.query("select * from user ", new DataClassRowMapper<>(User.class));
    }

    @DS("slave_1")
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<User> user01() {
        Service01 service01 = (Service01) AopContext.currentProxy();
        jdbcTemplate.execute("insert into user values (1,'张三')");
        List<User> users = service01.user03();
        return jdbcTemplate.query("select * from user ", new DataClassRowMapper<>(User.class));
    }


    @DS("slave_1")
//    @DSTransactional
    @Transactional
    public List<User> user03() {
        jdbcTemplate.execute("insert into user values (1,'张三')");
        return jdbcTemplate.query("select * from user ", new DataClassRowMapper<>(User.class));
    }

}
