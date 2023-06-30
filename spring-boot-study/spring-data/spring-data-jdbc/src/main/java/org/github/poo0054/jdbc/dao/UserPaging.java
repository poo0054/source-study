package org.github.poo0054.jdbc.dao;

import org.github.poo0054.jdbc.po.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author zhangzhi
 */
public interface UserPaging extends PagingAndSortingRepository<User, Long> {
}
