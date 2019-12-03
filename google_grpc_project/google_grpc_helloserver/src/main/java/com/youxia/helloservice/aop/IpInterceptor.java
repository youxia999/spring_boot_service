package com.youxia.helloservice.aop;

import io.grpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * Created by xiaogang on 2018/9/29.
 */
public class IpInterceptor implements ServerInterceptor {
    private Logger logger= LoggerFactory.getLogger(IpInterceptor.class);
    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata,
                  ServerCallHandler<ReqT, RespT> serverCallHandler) {
        InetSocketAddress remoteAddress = (InetSocketAddress) serverCall.getAttributes()
                .get(Grpc.TRANSPORT_ATTR_REMOTE_ADDR);
        logger.info("client ip is {}",remoteAddress.getHostString()+":"+remoteAddress.getPort());
        return serverCallHandler.startCall(serverCall,metadata);
    }

}
