package io.github.poo0054.security.mapper;

import io.github.poo0054.security.po.Role;
import io.github.poo0054.security.po.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author poo00
 */
@Mapper
public interface UsersMapper {

    @Select("select * from users where username=#{username}")
    Users selectUserByUsername(String username);

    @Select("select * from users where username=#{username}")
    Users selecRoleByUsername(String username);

    @Select("select r.* from users u,role_user ru, role r where " +
            "ru.uid=u.id and ru.rid = r.id and   uid=#{id}")
    List<Role> getRole(Long id);
}
