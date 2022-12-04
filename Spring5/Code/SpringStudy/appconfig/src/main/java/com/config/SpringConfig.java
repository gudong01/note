package com.config;

import com.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//这是一个配置类 相当于 applicationContext
@Configuration      //也会被Spring注册到容器中 因为他也是一个组件
@ComponentScan("com")
public class SpringConfig {
    //注册一个bean
    //方法的名字相当于 bean标签中的id
    //方法的返回值相当于 bean标签的class
    @Bean
    public User getUser(){
        return new User();  //返回的是要注入到bean的对象
    }
}
