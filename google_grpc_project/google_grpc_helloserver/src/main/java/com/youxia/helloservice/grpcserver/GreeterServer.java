package com.youxia.helloservice.grpcserver;

import com.youxia.helloservice.aop.IpInterceptor;
import com.youxia.helloservice.service.impl.GreeterImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

/**
 * Created by liu on 2018/9/29.
 */
@Component
public class GreeterServer
{
    private Server server;

    @Value("${spring.gateway.port}")
    private int port;

    @PostConstruct
    public void start() throws IOException {
        server=ServerBuilder.forPort(port)
                .addService(ServerInterceptors.intercept(new GreeterImpl(), new IpInterceptor()))
                .build().start();
        System.out.println("service start...");

    }

//    public void stop(){
//        if (server != null) {
//            server.shutdown();
//        }
//    }
//
//    @PreDestroy
//    // block 一直到退出程序
//    public void blockUntilShutdown() throws InterruptedException {
//        if (server != null) {
//            server.awaitTermination();
//        }
//        Runtime.getRuntime().addShutdownHook(new Thread(){
//            @Override
//            public void run() {
//                GreeterServer.this.stop();
//            }
//        });
//    }

}
