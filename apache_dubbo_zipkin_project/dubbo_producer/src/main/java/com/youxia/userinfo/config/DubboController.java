package com.youxia.userinfo.config;


import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/DubboAPI")
public class DubboController implements ApplicationContextAware {

    private final static Logger logger= LoggerFactory.getLogger(DubboController.class);

    private ApplicationContext applicationContext;

    @Autowired
    private HttpProviderConf httpProviderConf;
    private final Map<String, Class<?>> cacheMap = new HashMap<String, Class<?>>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    @ResponseBody
    @RequestMapping(value = "/{service}/{method}",method = RequestMethod.POST)
    public String api( HttpServletRequest httpServletRequest,HttpRequest httpRequest,
                      @PathVariable String service,@PathVariable String method){
        logger.info("ip-{},http-request {}",getIp(httpServletRequest), JSON.toJSONString(httpRequest));
        String invoke= invoke(httpRequest,service,method);
        logger.info("callback {}",invoke);
        return invoke;
    }

    private String invoke(HttpRequest httpRequest, String service, String method)  {
        httpRequest.setMethod(method);
        httpRequest.setService(service);
        HttpResponse httpResponse=new HttpResponse();
        if (!CollectionUtils.isEmpty(httpProviderConf.getUserPackage())){
            boolean isPac=false;
            for(String pac:httpProviderConf.getUserPackage()){
                if(service.startsWith(pac)){
                    isPac=true;
                    break;
                }
            }
            if (!isPac){
                logger.error("service is not  correct service is {}",service);
                httpResponse.setCode("2");
                httpResponse.setSuccess(false);
                httpResponse.setDescription("service is not conrect");
            }
        }
        Class<?> serviceClass=cacheMap.get(service);
        if (serviceClass==null){
            try {
                serviceClass=Class.forName(service);
                cacheMap.put(service,serviceClass);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (method == null){
            logger.error("method is not correct,method="+method);
            httpResponse.setCode("2");
            httpResponse.setSuccess(false);
            httpResponse.setDescription("method is not correct,method="+method);
        }
        Method[] methodArray=serviceClass.getMethods();
        Method targetMethod=null;
        Boolean isMethod=false;
        for (Method method1:methodArray){
            if (method1.getName().equals(method)){
                targetMethod=method1;
                isMethod=true;
                break;
            }
        }
        if (!isMethod){
            logger.error("method is not correct,method="+method);
            httpResponse.setCode("3");
            httpResponse.setSuccess(false);
            httpResponse.setDescription("method is not correct,method="+method);
            return JSON.toJSONString(httpResponse);
        }
        Object  bean=this.applicationContext.getBean(serviceClass);
        Object result=null;
        if (targetMethod!=null){
            Class<?>[] parameterTypes=targetMethod.getParameterTypes();
            try{
                if (parameterTypes.length==0){
                    result=targetMethod.invoke(bean);
                    return JSON.toJSONString(result);
                }else if(parameterTypes.length==1){
                    Object json=JSON.parseObject(httpRequest.getParam(),parameterTypes[0]);
                    result=targetMethod.invoke(bean,json);
                    return JSON.toJSONString(result);
                }else {
                    logger.error("can only have one parameter");
                    httpResponse.setCode("2");
                    httpResponse.setSuccess(false);
                    httpResponse.setDescription("can only have one parameter");
                }
            }
            catch (InvocationTargetException inException){
                logger.error("{}",inException);
            }catch(IllegalAccessException illException){
                logger.error("{}",illException);
            }
            return JSON.toJSONString(httpResponse);
        }
        return null;
    }

    private Object getIp(HttpServletRequest httpServletRequest) {
        if (httpServletRequest == null)
            return null;
        String s = httpServletRequest.getHeader("X-Forwarded-For");
        if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s)) {
            s = httpServletRequest.getHeader("Proxy-Client-IP");
        }
        if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s)) {
            s = httpServletRequest.getHeader("WL-Proxy-Client-IP");
        }
        if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s)) {
            s = httpServletRequest.getHeader("HTTP_CLIENT_IP");
        }
        if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s)) {
            s = httpServletRequest.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s)) {
            s = httpServletRequest.getRemoteAddr();
        }
        if ("127.0.0.1".equals(s) || "0:0:0:0:0:0:0:1".equals(s))
            try {
                s = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException unknownhostexception) {
                return "";
            }
        return s;
    }


}
