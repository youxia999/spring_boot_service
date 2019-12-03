package com.youxia.springboot.dubbo.domain;

public class UserInfo  implements java.io.Serializable {
    private String  userName;
    private int userAge;
    private String userJob;
    private Double userSalary;
    private String userAddress;
    private String userPhone;
    private long userId;

    public void setUserSalary(Double userSalary) {
        this.userSalary = userSalary;
    }

    public Double getUserSalary() {
        return userSalary;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserJob() {
        return userJob;
    }

    public void setUserJob(String userJob) {
        this.userJob = userJob;
    }


    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
