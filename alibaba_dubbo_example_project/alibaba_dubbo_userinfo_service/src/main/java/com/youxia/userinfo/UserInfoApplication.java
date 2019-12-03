package com.youxia.userinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class UserInfoApplication
{
    public static void main( String[] args )
    {
        SpringApplication  springApplication=new SpringApplication();
        springApplication.run(UserInfoApplication.class,args);
    }
}
