package io.github.poo0054.security.po;

import lombok.Data;

import java.util.Set;

/**
 * @author zhangzhi
 */
@Data
public class Role {
    private Long id;
    private String name;

    private Set<String> permission;
}
