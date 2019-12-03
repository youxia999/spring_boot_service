package com.youxia.userinfo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

@Component
public class HttpProviderConf {

    @Value("${conf.package}")
    private String usePackageString;
    private  List<String> userPackage;

    public List<String> getUserPackage() {
        return userPackage;
    }

    public void setUserPackage(List<String> userPackage) {
        this.userPackage =Arrays.asList(StringUtils.split(usePackageString,","));
    }
}
