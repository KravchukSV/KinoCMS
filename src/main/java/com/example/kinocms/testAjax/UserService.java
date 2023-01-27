package com.example.kinocms.testAjax;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private List<User> users;

    // Love Java 8
    public List<User> findByUserNameOrEmail(String username) {

        List<User> result = users.stream().filter(x -> x.getUsername().equalsIgnoreCase(username)).collect(Collectors.toList());

        return result;

    }
    // Init some users for testing
    @PostConstruct
    private void iniDataForTesting() {
        users = new ArrayList<User>();
        User user1 = new User("maxsu", "password111", "maxsu@yahoo.com");
        User user2 = new User("yiflow", "password222", "yflow@yahoo.com");
        User user3 = new User("minaxu", "password333", "minaxu@yahoo.com");
        User user4 = new User("minalee", "password333", "minalee@yahoo.com");
        User user5 = new User("yiiflow", "password333", "yiiflow@gmail.com");

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
    }
}
//更多请阅读：https://www.yiibai.com/spring-boot/ajax-example.html

