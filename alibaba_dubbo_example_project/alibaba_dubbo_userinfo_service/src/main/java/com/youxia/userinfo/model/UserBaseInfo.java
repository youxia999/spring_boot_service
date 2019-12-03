package com.youxia.userinfo.model;

import java.util.Date;


public class UserBaseInfo {

    private Integer id;

    private String userName;


    private String userPhone;


    private String userJob;


    private Double userSalary;


    private String userAddress;


    private int userAge;


    private Date createTime;

    private Date modifyTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return user_phone
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * @param userPhone
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    /**
     * @return user_job
     */
    public String getUserJob() {
        return userJob;
    }

    /**
     * @param userJob
     */
    public void setUserJob(String userJob) {
        this.userJob = userJob;
    }

    /**
     * @return user_salary
     */
    public Double getUserSalary() {
        return userSalary;
    }

    /**
     * @param userSalary
     */
    public void setUserSalary(Double userSalary) {
        this.userSalary = userSalary;
    }

    /**
     * @return user_address
     */
    public String getUserAddress() {
        return userAddress;
    }

    /**
     * @param userAddress
     */
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    /**
     * @return user_age
     */
    public int getUserAge() {
        return userAge;
    }

    /**
     * @param userAge
     */
    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return modify_time
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * @param modifyTime
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}