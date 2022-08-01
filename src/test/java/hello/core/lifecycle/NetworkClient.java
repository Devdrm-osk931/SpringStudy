package hello.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient{

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출 url = " + url);
//        connect();
//        call("초기화 연결 메세지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작 시 호출하는 메소드
    public void connect() {
        System.out.println("NetworkClient.connect");
        System.out.println("connect: " + url);
    }

    //연결이 된 상태에서 호출하는 메소드
    public void call(String message) {
        System.out.println("NetworkClient.call");
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료 시 호출
    public void disconnect() {
        System.out.println("NetworkClient.disconnect");
        System.out.println("close: " + url);
    }

    @PostConstruct
    public void init(){
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메세지");
    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}
