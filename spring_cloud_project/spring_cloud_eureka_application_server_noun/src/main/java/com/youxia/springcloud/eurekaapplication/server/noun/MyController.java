package com.youxia.springcloud.eurekaapplication.server.noun;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by youxia on 2016/11/24.
 */
@RestController
public class MyController {

    @Value("${words}") String words;

    @RequestMapping("/")
    public  String getWord() {
        String[] wordArray = words.split(",");
        int i = (int)Math.round(Math.random() * (wordArray.length - 1));
        return wordArray[i];
    }
}
