package org.github.poo0054.rest;

import org.github.rest.RestTemplateApplication;
import org.github.rest.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhangzhi
 */
@SpringBootTest(classes = RestTemplateApplication.class)
public class RestTemplateTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void variables() {
//        RequestEntity<Void> build = RequestEntity.get("http://localhost:8088/variables/{?}", "i is test").build();
        RequestEntity<Void> build = RequestEntity.get("http://localhost:8088/variables/{test}", "i is test").build();
        ResponseEntity<String> exchange = restTemplate.exchange(build, String.class);
        System.out.println(exchange);
    }

    @Test
    void parameter() {
//        RequestEntity<Void> requestEntity = RequestEntity.get("http://localhost:8088/parameter?name={?}&id={?}", "i is name", 1).build();
        RequestEntity<Void> requestEntity = RequestEntity.get("http://localhost:8088/parameter?name={name}&id={id}", "i is name", 1).build();
        ResponseEntity<String> exchange = restTemplate.exchange(requestEntity, String.class);
        System.out.println(exchange);
    }

    @Test
    void user() {
        User user = new User();
        RequestEntity<User> userRequestEntity = RequestEntity.method(HttpMethod.GET, "http://localhost:8088/user").body(user);
        ResponseEntity<User> exchange = restTemplate.exchange(userRequestEntity, User.class);
        System.out.println(exchange);
    }

    @Test
    void parameterUser() {
        RequestEntity<Void> requestEntity = RequestEntity.get("http://localhost:8088/user?name={name}&id={id}", "i is name", 1).build();
        ResponseEntity<User> exchange = restTemplate.exchange(requestEntity, User.class);
        System.out.println(exchange);
    }


    @Test
    void postUser() {
        User user = new User();
        RequestEntity<User> userRequestEntity = RequestEntity.method(HttpMethod.POST, "http://localhost:8088/postUser").body(user);
        ResponseEntity<User> exchange = restTemplate.exchange(userRequestEntity, User.class);
        System.out.println(exchange);
    }

    @Test
    void head() {
        RequestEntity<Void> userRequestEntity = RequestEntity
                .method(HttpMethod.GET, "http://localhost:8088/head")
                .header("name", "i is name")
                .build();
        ResponseEntity<String> exchange = restTemplate.exchange(userRequestEntity, String.class);
        System.out.println(exchange);
    }
}
