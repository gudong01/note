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
