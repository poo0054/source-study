package org.github.poo0054.flow.po;

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
