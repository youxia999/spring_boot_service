package com.youxia.helloservice.service.impl;

import com.youxia.helloservice.GreeterGrpc;
import com.youxia.helloservice.HelloReply;
import com.youxia.helloservice.HelloRequest;
import io.grpc.stub.StreamObserver;



/**
 * Created by xiaogang on 2018/9/29.
 */
public class GreeterImpl extends GreeterGrpc.GreeterImplBase {
    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        HelloReply reply=HelloReply.newBuilder().setMessage("hello:"+request.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
