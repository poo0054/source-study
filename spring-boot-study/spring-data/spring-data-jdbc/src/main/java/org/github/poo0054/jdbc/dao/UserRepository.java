package org.github.poo0054.jdbc.dao;

import org.github.poo0054.jdbc.po.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author zhangzhi
 */
public interface UserRepository extends CrudRepository<User, Long> {

}
