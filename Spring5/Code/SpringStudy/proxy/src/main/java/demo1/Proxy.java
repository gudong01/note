package demo1;

public class Proxy {
    //优先通过组合而不是接口
    private Host host;
    public Proxy(){}
    public Proxy(Host host){
        this.host = host;
    }

    public void rent(){
        host.rent();
    }
    //看房
    public void seeHouse(){
        System.out.println("中介带你看房");
    }
    //收费
    public void fare(){
        System.out.println("收费");
    }
    //签合同
    public void qht(){
        System.out.println("签合同");
    }
}
