package com.youxia.userinfo.config;

import brave.context.slf4j.MDCScopeDecorator;
import brave.propagation.CurrentTraceContext;
import brave.spring.beans.CurrentTraceContextFactoryBean;
import brave.spring.beans.TracingFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zipkin2.codec.SpanBytesEncoder;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Sender;
import zipkin2.reporter.beans.AsyncReporterFactoryBean;
import zipkin2.reporter.okhttp3.OkHttpSender;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
public class ZipkinConfig {

    @Value("${zipkin.server.url}")
    private String ZipkinServerUrl;
//    @Bean("okHttpSender")
//    public OkHttpSenderFactoryBean okHttpSender(){
//        OkHttpSenderFactoryBean okHttpSenderFactoryBean = new OkHttpSenderFactoryBean();
//        //zipkin服务端
//        okHttpSenderFactoryBean.setEndpoint("http://localhost:9411/api/v2/spans");
//        return okHttpSenderFactoryBean;
//    }

    @Bean("okHttpSender")
    public Sender okHttpSender() {
        Sender sender= OkHttpSender.create(ZipkinServerUrl);
        return sender;
    }

    @Bean("reporter")
    public AsyncReporter getAsyncReporter(){
        AsyncReporter asyncReporter=AsyncReporter.builder(okHttpSender()).closeTimeout(50000, TimeUnit.MILLISECONDS).build(SpanBytesEncoder.JSON_V2);
        return asyncReporter;
    }

    @Bean
    public AsyncReporterFactoryBean reporter(@Qualifier("okHttpSender")OkHttpSender sender){
        AsyncReporterFactoryBean asyncReporterFactoryBean = new AsyncReporterFactoryBean();
        asyncReporterFactoryBean.setSender(sender);
        asyncReporterFactoryBean.setCloseTimeout(3000);
        return asyncReporterFactoryBean;
    }



    @Bean
    public TracingFactoryBean getTracingBean(@Qualifier("reporter") AsyncReporter reporter){
        TracingFactoryBean tracingFactoryBean=new TracingFactoryBean();
        tracingFactoryBean.setLocalServiceName("userinfo-service");
        CurrentTraceContextFactoryBean currentTraceContextFactoryBean = new CurrentTraceContextFactoryBean();
        CurrentTraceContext.ScopeDecorator scopeDecorator = MDCScopeDecorator.create();
        currentTraceContextFactoryBean.setScopeDecorators(Arrays.asList(scopeDecorator));
        tracingFactoryBean.setCurrentTraceContext(currentTraceContextFactoryBean.getObject());
        tracingFactoryBean.setSpanReporter(reporter);
        return tracingFactoryBean;
    }

}
