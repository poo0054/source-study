package org.github.poo0054.flow.dao;

import org.github.poo0054.flow.po.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author zhangzhi
 */
public interface UserPaging extends PagingAndSortingRepository<User, Long> {
}

//public interface UserPaging extends ReactiveCrudRepository<User, Long> {
//}
