import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserService;
import service.UserServiceImpl;

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
