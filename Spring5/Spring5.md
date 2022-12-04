# 1.Spring

## 1.1简介

* [Spring Framework 中文文档 - Language Support | Docs4dev](https://www.docs4dev.com/docs/zh/spring-framework/5.1.3.RELEASE/reference/languages.html#dynamic-language)

* 设计理念：解决企业开发的复杂性，使现有的技术更加容易使用。
* SSM SpringMvc +Spring +Mybatis

## 1.2 优点

* 开源的免费的框架

* 轻量级的，非入侵式的框架

* ###### 控制反转(IOC)，面向切面编程(AOP)

* 支持事务的处理，对框架整合的支持

**Spring是一个支持IO 和AOP的轻量级的非入侵式的框**

## 1.3组成

![image-20221203021623791](C:\Users\咕咚\AppData\Roaming\Typora\typora-user-images\image-20221203021623791.png)

## 1.4 拓展

* SpringBoot
  * 一个快速开发的脚手架
  * 基于SpringBoot可以快速开发单个微服务
* SpringCloud
  * 基于Spring Boot实现



# 2.IOC 理论推导

```java
<!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.3.20</version>
        </dependency>
```

* 用接口实现对象的注入
* 程序由主动的创建对象 变为 别动的接收对象（程序员不再需要管理对象的创建）
* 获得依赖的对象反转了



# 3.Hello Spring

* 将bean交给Spring容器管理 创建装配

* Hello.java

```java
public class Hello {
    private String str;
}	
//省略get.set.toString
```

* beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>

<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--使用Spring 来创建对象
     bean 相当于 new 对象
     id 给bean取名字
     property 给bean的属性赋值
     -->
    <bean id="hello" class="Hello">
        <property name="str" value="Spring"> </property>
    </bean>
</beans>
```

* MyTset.java

```java
public class MyTest {
    public static void main(String[] args) {
        //获取Spring的上下文对象(容器)
        ApplicationContext context = new ClassPathXmlApplicationContext( "beans.xml");
        Hello hello = (Hello)context.getBean("hello");
        System.out.println(hello.toString());
    }
}
```

# 4.IOC创建对象的方式

1.使用无参构造创建对象（默认）

2.有参构造(三种)

```java
<!--    index注入-->
    <bean id="user" class="User">
        <constructor-arg index="0" value="index 赋值"> </constructor-arg>
    </bean>
<!--    类型注入-->
    <bean id="user" class="User">
        <constructor-arg type="java.lang.String" value="类型"> </constructor-arg>
    </bean>
<!--    通过参数名-->
    <bean id="user" class="User">
        <constructor-arg name="name" value="参数名"> </constructor-arg>
    </bean>
```

总结·： 在配置文件加载时，容器中管理的所有对象已经被初始化了

# 5.Spring 配置

## 5.1 别名	alias

```java
//context可以直接使用别名取bean
<alias name="user" alias="myUser"/>
```

## 5.2 Bean的配置

```java
//id ：bean的唯一标识符
//class ：bean对象对应的class的全限定名 (包名加类名)
//name : 也相当于别名 而且可以取多个别名 （逗号或者空格分割）
<bean id="user" class="User" name="user2 user3 user4">
```

## 5.3 import

```java
//一般用于团队开发 可以将多个配置文件导入一个
//一般在applicationContext.xml import 所有的beans.xml
<import resource="beans2.xml"/>
```

# 6.依赖注入（DI）

## 6.1 构造器注入



## 6.2 set注入【★】

* 依赖注入
  * 依赖：bean对象的创建依赖于容器
  * 注入：bean中所有的属性有容器注入

* Student.java

```java
public class Student {
    private String name;
    private Address address;
    private String[] books;
    private List<String> hobbies;
    private Map<String,String> card;
    private Set<String> games;
    private String wife;
    private Properties info;
}	//set get toString
```

* Address.java

```java
private String address;
//get set toString
```

* applicationContext.xml

```java
	<bean id="address" class="Address">
        <property name="address" value="荷花街道"> </property>
    </bean>

    <bean id="student" class="Student">
<!--    value类型-->
        <property name="name" value="潘潘"> </property>
<!--    bean注入-->
        <property name="address" ref="address"> </property>
<!--    数组注入-->
        <property name="books">
            <array>
                <value>红楼梦</value> 
        		<value>西游记</value> 
        		<value>水浒传</value> 
        		<value>三国演义</value>
            </array>
        </property>
<!--    list注入-->
        <property name="hobbies">
            <list>
                <value>唱</value> 
        		<value>跳</value> 
        		<value>rap</value> 
        		<value>篮球</value>
            </list>
        </property>
<!--    map注入-->
        <property name="card">
            <map>
                <entry key="身份证" value="321321321232334"> </entry>
                <entry key="校园卡" value="1923402010"> </entry>
            </map>
        </property>
<!--    set注入-->
        <property name="games">
            <set>
                <value>NBA2K</value> 
        		<value>COD19</value>
            </set>
        </property>
<!--    null注入-->
        <property name="wife">
            <null/>
        </property>
<!--    property注入-->
        <property name="info">
            <props>
                <prop key="学号">123</prop>
            </props>
        </property>
    </bean>
```

* p空间空间 注入

```xml
//无参构造
xmlns:p="http://www.springframework.org/schema/p"		//导入约束
<bean id="user" class="User" p:name="猪头" p:age="13">
```

* c命名空间 注入

```xml
//通过有参构造
xmlns:c="http://www.springframework.org/schema/c"		//导入约束
<bean id="user" class="User" c:_0="小猪" c:_1="18"> </bean>
```

## 6.3 BeanScope (作用域)

```xml
//1.singleton	单例
//2.prototype	原型模式 每次从容器get都是新的对象
//3.request	application session		(只存在web中)

<bean id="user" class="User" c:_0="小猪" c:_1="18" scope="singleton"> </bean>
```

# 7 bean的自动装配

* 自动装配是Spring满足bean依赖的一种方式
* Spring会在上下文中自动寻找，并自动给bean装配属性



三种装配的方式

1.xml中显示配置

2.java中显示配置

3.隐式的自动装配到bean【★】

## 7.1 测试

* 环境 ： 一个人有两个宠物

## 7.2 自动装配

```xml
	<bean id="dog" class="Dog"> </bean>
    <bean id="cat" class="Cat"> </bean>
    <!--
    autowire="byName/byType"
    byName在容器上下文中找到名字相同的bean (person中的属性)
    byType在容器上下文中找到类型相同的bean (person中的属性)
    -->
    <bean id="person" class="Person" autowire="byType">
        <property name="name" value="小潘"/>
    </bean>
```

## 7.4使用注解实现自动开发

1.使用注解须知

* 导入约束		context约束
* 配置注解的支持 <<context:annotation-config/>>

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">
    <!--开启注解支持-->
    <context:annotation-config/>    
    <bean id="dog" class="Dog"> </bean>
    <bean id="cat" class="Cat"> </bean>
    <bean id="person" class="Person"> </bean>
</beans>
```

```java
//在属性上写注解Autowired 实现自动装配
public class Person {
    @Autowired(required = false)
    @Qualifier(value = "cat22")         //实现指定装配bean
    private Cat cat;
    @Autowired
    private Dog dog;
    @Value("潘星宇")
    private String name;
    //...
}
```

* Autowired 和 Resouce 的区别

```java
1.@Autowired由Spring提供 @Resouce由JDK提供
2.Autowired默认的注入方式是byType Resouce默认的注入方式是byName
3.当一个接口有多个实现类的情况下 都需要通过名称来正确匹配到对应的Bean
    Autowired 可以通过 @Qualifier注解来指定名称
    Resource 可以通过name属性来自定名称
```



# 8.使用注解开发

Spring4之后，要使用注解开发必须导入aop的包

使用注解要导入context约束 增加注解的支持

```xml
<context:annotation-config/>	    <!--开启注解支持-->
<context:component-scan base-package="com"/> 	<!--扫描com包下的组件-->
```



1.bean

2.属性注入

```java
//等价于在容器中注册了组件
//<bean id="user" class="User"> <bean/>
@Component
public class User {
    @Value("潘潘")
    public  String name;
}
```

3.衍生的注解

```java
@Service		service
@Repository		dao
@Controller		controller
```

4.bean的自动装配

5.作用域

```java
@scope(value = "singleton")
```

6.小结

* 注解和配置文件
  * xml更加万能，适用于任何场合 维护更加简单
  * 注解不是自己的类使用不了，维护相对复杂

* 最佳实践
  * xml用来管理Bean
  * 注解只负责完成属性的注入

# 9 使用javaConfig配置Spring

此处完全不使用Spring的xml配置，完全使用java来做

javaConfig 是 Spring的一个子项目 Spring4之后成为了核心功能

* User.java

```java
package com;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
public class User {
    @Value("nnn")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
```

SpringConfig.java

```java
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
```

* MyTest.java

```java
public class MyTest {
    @Test
    public void test(){
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        User user = context.getBean("getUser", User.class);
        System.out.println(user);
    }
}
```



# 10 代理模式

## 10.1静态代理

角色分析：

* 抽象角色：一般使用抽象类或抽象接口解决
* 真实角色：被代理的角色
* 代理角色 ：代理真实角色，一般会有一些附属操作
* 客户：访问代理对象的人

代理模式的好处

* 可以是真实角色的操作更加纯粹
* 公共操作交给代理角色，实现业务的分工
* 公共业务发生拓展时候，方便集中管理
* ==不用改变原有的业务代码，增加业务功能==

缺点

* 一个真实角色就会产生一个代理角色，代码翻倍

## 10.2 动态代理

* 角色分析 与 静态代理一致

* 动态代理是代理类自动生成的，区别于静态代理的自己编写

* 动态代理分为两大类 ：基于接口的动态代理 基于类的动态代理

  * 基于接口：JDK 动态代理
  * 基于类：cglib
  * Java字节码实现：javasist

  需要了解两个类：

  java.lang.reflect.Proxy ：代理  

  java.lang.reflect.InvocationHandler：调用处理

```java
package demo2;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

//实现InvocationHandler接口
public class DynamicProxy implements InvocationHandler {
    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //这里可以进行一些代理类的额附加操作
        System.out.println("代理收费");
        //执行被代理对象的操作
        Object result = method.invoke(target, args);
        return result;
    }
}

```

```java
package demo2;

import demo1.Host;
import demo1.Rent;

import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        //真实对象
        Host host = new Host();
        //设置要代理的对象
        DynamicProxy dynamicProxy = new DynamicProxy();
        dynamicProxy.setTarget(host);
        //生成代理类（基于接口实现）
        Rent proxy = (Rent) Proxy.newProxyInstance(Rent.class.getClassLoader(), new Class<?>[]{Rent.class}, dynamicProxy);
        proxy.rent();
    }
}
```

# 11 Aop

* 方式一 使用原生Spring Aop

  * applicationContext.xml

  ```xml
  <!--注册bean -->
      <bean id="userService" class="service.UserServiceImpl"> </bean>
      <bean id="log" class="Log.Log"> </bean>
      <!--配置aop 先导入aop的约束-->
      <aop:config>
          <!--切入点 expression="执行的位置" // 返回值 + 函数名 + 参数列表-->
          <!--expression="execution(* service.UserServiceImpl.*(..))"表示匹配service.UserServiceImpl的任何方法-->
          <aop:pointcut id="pointcut" expression="execution(* service.UserServiceImpl.*(..))"/>
          <!--执行环绕增加-->
          <aop:advisor advice-ref="log" pointcut-ref="pointcut"/>
      </aop:config>
  ```

  ```java
  public class MyTest
  {
      @Test
      public void test01(){
          ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
          //虽然传入的bean是实现类，但是动态代理的额返回类型是一个接口，他代理的是接口
          UserService userService = (UserService)context.getBean("userService");
          userService.add();
      }
  }
  ```

* 自定义切面类

  * 切面类

  ```java
  public class MyAspect {
      public void myBefore(){
          System.out.println("myBefore");
      }
      public void myAfter(){
          System.out.println("myAfter");
      }
  }
  ```

  

  * xml

  ```xml
  <bean id="myAspect" class="Log.MyAspect"> </bean>
  
      <aop:config>
          <aop:aspect ref="myAspect">
              <aop:before method="myBefore" pointcut-ref="pointcut"/>
              <aop:after method="myAfter" pointcut-ref="pointcut"/>
          </aop:aspect>
      </aop:config>
  
  ```



* 注解实现

```java
@Component
@Aspect         //定义一个切面类
public class AopAnnotation {
    @Before("execution(* service.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("before");
    }
    @After(("execution(* service.UserServiceImpl.*(..))"))
    public void after(){
        System.out.println("after");
    }

    @Around(("execution(* service.UserServiceImpl.*(..))"))
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around before");
        Object o = joinPoint.proceed();
        System.out.println("around after");
        return o;
    }
    @AfterReturning("execution(* service.UserServiceImpl.*(..))")
    public void afterReturning(){
        System.out.println("afterReturning");
    }
}

//around before
//before
//增加一个用户
//afterReturning
//after
//around after
```

* Aop的个人理解 ： 其实就是代理模式的实现，在不改变原有代码的情况下，实现代码业务的增强

# 12 整合MyBatis

步骤

1.导入jar包

2.编写配置

3.测试

