package org.github.poo0054.jdbc.po;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author zhangzhi
 */
@Data
public class User {
    @Id
    private Long id;
    private String name;
}
