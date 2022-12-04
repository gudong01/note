import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext( "applicationContext.xml");
        Student s = (Student) context.getBean("student");
        System.out.println(s.toString());
    }
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext( "userBeans.xml");
        User user = (User) context.getBean("user");
        System.out.println(user);

    }

}
