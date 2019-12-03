package com.youxia.helloservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class HelloServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication springApplication=new     SpringApplication();
        springApplication.run(HelloServiceApplication.class,args);
    }

}
