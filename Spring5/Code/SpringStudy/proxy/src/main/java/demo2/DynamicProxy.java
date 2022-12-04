package demo2;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

//实现InvocationHandler接口
public class DynamicProxy implements InvocationHandler {
    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }
//    public Object getProxy(){
//        return
//    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //这里可以进行一些代理类的额附加操作
        System.out.println("代理收费");
        //执行被代理对象的操作
        Object result = method.invoke(target, args);
        return result;
    }
}
