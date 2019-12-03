package com.youxia.gateway.controller;

import com.youxia.gateway.remote.GreeterServiceGrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xiaogang on 2018/9/29.
 */
@Controller
public class IndexController {
    @Autowired
    private GreeterServiceGrpcClient client;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String sayHello(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String msg = client.greet(name);
        return msg ;
    }
}
