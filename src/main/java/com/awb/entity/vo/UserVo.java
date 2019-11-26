package com.awb.entity.vo;

/**
 * Created by Administrator on 2019/8/22.
 */
public class UserVo {
    private String phone;
    private String name;
    private String identitycard;
    private Integer admnum;
    private Double balance;
    private String tgm;
    private Integer nodelever;

    public Integer getNodelever() {
        return nodelever;
    }

    public void setNodelever(Integer nodelever) {
        this.nodelever = nodelever;
    }

    public String getTgm() {
        return tgm;
    }

    public void setTgm(String tgm) {
        this.tgm = tgm;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentitycard() {
        return identitycard;
    }

    public void setIdentitycard(String identitycard) {
        this.identitycard = identitycard;
    }

    public Integer getAdmnum() {
        return admnum;
    }

    public void setAdmnum(Integer admnum) {
        this.admnum = admnum;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
