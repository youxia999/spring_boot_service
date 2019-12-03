package com.youxia.userinfo.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@DubboComponentScan(basePackages = "com.youxia.userinfo.service")
public class DubboProviderConfig {
    @Bean
    public RegistryConfig registryConfig(){
        RegistryConfig  registryConfig=new RegistryConfig();
        registryConfig.setProtocol("redis");
        registryConfig.setAddress("192.168.172.4");
        registryConfig.setPort(6380);
        return registryConfig;
    }

    @Bean
    public ApplicationConfig getApplication(){
        ApplicationConfig applicationConfig=new ApplicationConfig();
        applicationConfig.setName("user_info_service");
        return applicationConfig;
    }

    @Bean
    public ProviderConfig getProviderConfig(){
        ProviderConfig providerConfig=new ProviderConfig();
        providerConfig.setTimeout(10000);
        return providerConfig;
    }

    @Bean
    public ProtocolConfig getProtocolConfig(){
        ProtocolConfig protocolConfig=new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(28083);
        return protocolConfig;
    }
}
