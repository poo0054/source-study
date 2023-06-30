package org.github.poo0054.rest.dao;

import org.github.poo0054.rest.po.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author zhangzhi
 */
@RepositoryRestResource
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

}
