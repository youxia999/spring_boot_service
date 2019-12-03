package com.youxia.userinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class ServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication serverApplication=new SpringApplication();
        serverApplication.run(ServiceApplication.class,args);
    }
}
