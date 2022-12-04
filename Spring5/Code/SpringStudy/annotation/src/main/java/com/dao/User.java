package com.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//等价于在容器中注册了Bean
//<bean id="user" class="User"> <bean/>
@Component
public class User {
    @Value("潘潘")
    public  String name;
}
