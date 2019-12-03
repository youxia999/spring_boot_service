package com.youxia.gateway.remote;


import com.youxia.helloservice.GreeterGrpc;
import com.youxia.helloservice.HelloReply;
import com.youxia.helloservice.HelloRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiaogang on 2018/9/29.
 */
@Component
public class GreeterServiceGrpcClient {

    private final ManagedChannel channel;

    private final GreeterGrpc.GreeterBlockingStub blockingStub;


    private String ip;


    private int port;

    /**
     * 初始化链接
     */
    public GreeterServiceGrpcClient(@Value("${remote.greeterservice.ip}") String ip,
                                    @Value("${remote.greeterservice.port}")int port){
        channel= ManagedChannelBuilder.forAddress(ip,port).usePlaintext(true).build();
        blockingStub= GreeterGrpc.newBlockingStub(channel);
    }

    /**
     * 关闭链接
     * @throws InterruptedException
     */
    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);

    }

    /**
     * 业务接口调用
     * @param name
     * @return
     */
    public String greet(String name){
        HelloRequest request= HelloRequest.newBuilder().setName(name).build();
        HelloReply reply=blockingStub.sayHello(request);
       return  reply.getMessage();
    }
}
