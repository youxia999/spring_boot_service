package com.youxia.gateway.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@DubboComponentScan(basePackages = "com.youxia.gateway.service.userinfo")
public class ConsumerDubboConfig {
    @Bean
    public RegistryConfig getRegistryConfig(){
        RegistryConfig registryConfig=new RegistryConfig();
        registryConfig.setAddress("192.168.172.4");
        registryConfig.setProtocol("redis");
        registryConfig.setPort(6380);
        return registryConfig;
    }

    @Bean
    public ConsumerConfig getConsumerConfig(){
        ConsumerConfig consumerConfig=new ConsumerConfig();
        consumerConfig.setTimeout(10000);
        return consumerConfig;
    }

    @Bean
    public ApplicationConfig getApplicationConfig(){
        ApplicationConfig applicationConfig=new ApplicationConfig();
        applicationConfig.setName("dubbo-consumer");
        return applicationConfig;
    }

}
