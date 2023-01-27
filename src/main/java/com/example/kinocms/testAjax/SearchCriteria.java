package com.example.kinocms.testAjax;

import jakarta.validation.constraints.NotBlank;

public class SearchCriteria {

    @NotBlank(message = "username can't empty!")
    String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
//更多请阅读：https://www.yiibai.com/spring-boot/ajax-example.html


