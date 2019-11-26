package com.awb.entity.vo;

import java.util.Date;

/**
 * Created by Administrator on 2019/8/28.
 */
public class AdminUserVo {
    private String id;
    private String phone;
    private String name;
    private String identitycard;
    private String parentname;
    private Integer times;
    private Integer admnum;
    private Integer status;
    private String tgm;
    private Integer nodelever;
    private Double balance;
    private Double money;
    private Date createtime;
    private Date updatetime;
    private Double totalmoney;
    private Integer totalnum;
    private  String parentphone;
    private Integer type;
    private  Integer coupontimes;

    public Integer getCoupontimes() {
        return coupontimes;
    }

    public void setCoupontimes(Integer coupontimes) {
        this.coupontimes = coupontimes;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getParentphone() {
        return parentphone;
    }

    public void setParentphone(String parentphone) {
        this.parentphone = parentphone;
    }

    public Double getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(Double totalmoney) {
        this.totalmoney = totalmoney;
    }

    public Integer getTotalnum() {
        return totalnum;
    }

    public void setTotalnum(Integer totalnum) {
        this.totalnum = totalnum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Integer getAdmnum() {
        return admnum;
    }

    public void setAdmnum(Integer admnum) {
        this.admnum = admnum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTgm() {
        return tgm;
    }

    public void setTgm(String tgm) {
        this.tgm = tgm;
    }

    public Integer getNodelever() {
        return nodelever;
    }

    public void setNodelever(Integer nodelever) {
        this.nodelever = nodelever;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
