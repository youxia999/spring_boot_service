package com.youxia.userinfo.config;

import java.io.Serializable;

public class HttpResponse implements Serializable {
    private boolean success;
    private String code;
    private  String description;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
