package com.youxia.authsever.controller;

import com.youxia.authsever.entity.User;
import com.youxia.authsever.mapper.UserMapper;
import com.youxia.authsever.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.common.exceptions.RedirectMismatchException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedResponseTypeException;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Controller
public class LoginController {

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin(){
        return "login";
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String postIndex(){
        return "index";
    }

    @RequestMapping(value = "/oauth_approval", method=RequestMethod.POST)
    public ModelAndView postOauthApproval(Map<String, Object> model, HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView("oauth_approval");
        //AuthorizationRequest authorizationRequest = (AuthorizationRequest) session.getAttribute("authorizationRequest");
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        model.put("auth", authentication);
        modelAndView.addAllObjects(model);
        return modelAndView;
    }

    @RequestMapping(value = "/oauth_approval", method = RequestMethod.GET)
    public String getOauthApproval(){
        return "oauth_approval";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User getIndex(@RequestParam("username") String username, @RequestParam("password") String password){
        User user=userMapper.findByUsername(username);
        user.setPassword(user.getPassword().substring("{bcrypt}".length()));
        return user;
    }

    @RequestMapping(value = "/error", method = RequestMethod.POST)
    public String getError(){
        return "error";
    }


    @RequestMapping(value = "/registry", method = RequestMethod.POST)
    public User createUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)) {
            return userService.create(username, password);
        }
        return null;
    }
}
