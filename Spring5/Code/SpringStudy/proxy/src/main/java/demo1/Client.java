package demo1;

public class Client {
    public static void main(String[] args) {
        Host host = new Host();
        //代理    会有一些附属操作
        Proxy proxy = new Proxy(host);
        proxy.seeHouse();
        proxy.fare();
        proxy.rent();
        proxy.qht();
    }
}
