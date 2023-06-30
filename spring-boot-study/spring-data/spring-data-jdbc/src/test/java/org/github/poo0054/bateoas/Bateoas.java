package org.github.poo0054.bateoas;

import org.github.poo0054.jdbc.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.mediatype.hal.HalModelBuilder;

/**
 * @author zhangzhi
 */
public class Bateoas {
    @Test
    void test() {
        User user = new User();
        user.setId(1L);
        EntityModel<User> of = EntityModel.of(user);
        System.out.println(of);
        HalModelBuilder halModelBuilder = HalModelBuilder.halModelOf(user);
    }
}
