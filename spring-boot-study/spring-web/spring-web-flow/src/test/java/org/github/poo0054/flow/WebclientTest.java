package org.github.poo0054.flow;

import org.github.poo0054.flow.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

/**
 * @author zhangzhi
 */
public class WebclientTest {

    @Test
    void test() {
        Flux<User> userFlux = WebClient.create("http://localhost:8003/user")
                .get()
                .retrieve()
                .bodyToFlux(User.class);
        userFlux.subscribe(user -> {
            System.out.println(user);
        });
    }
}
