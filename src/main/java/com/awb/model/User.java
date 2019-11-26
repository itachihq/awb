package com.awb.model;

import java.util.Date;

public class User {
    private String id;

    private String phone;

    private String name;

    private String identitycard;

    private String password;

    private String parentid;

    private String agentarea;

    private String superiorid;

    private String storename;

    private String tradePassword;

    private Integer times;

    private Integer admnum;

    private Integer totalnum;

    private Integer status;

    private Integer flg;

    private Integer type;

    private String tgm;

    private Integer nodelever;

    private Integer isDelete;

    private Double balance;

    private Double money;

    private Double totalmoney;

    private Date createtime;

    private Date updatetime;

    private Integer coupontimes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIdentitycard() {
        return identitycard;
    }

    public void setIdentitycard(String identitycard) {
        this.identitycard = identitycard == null ? null : identitycard.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid == null ? null : parentid.trim();
    }

    public String getAgentarea() {
        return agentarea;
    }

    public void setAgentarea(String agentarea) {
        this.agentarea = agentarea == null ? null : agentarea.trim();
    }

    public String getSuperiorid() {
        return superiorid;
    }

    public void setSuperiorid(String superiorid) {
        this.superiorid = superiorid == null ? null : superiorid.trim();
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename == null ? null : storename.trim();
    }

    public String getTradePassword() {
        return tradePassword;
    }

    public void setTradePassword(String tradePassword) {
        this.tradePassword = tradePassword == null ? null : tradePassword.trim();
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

    public Integer getTotalnum() {
        return totalnum;
    }

    public void setTotalnum(Integer totalnum) {
        this.totalnum = totalnum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getFlg() {
        return flg;
    }

    public void setFlg(Integer flg) {
        this.flg = flg;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTgm() {
        return tgm;
    }

    public void setTgm(String tgm) {
        this.tgm = tgm == null ? null : tgm.trim();
    }

    public Integer getNodelever() {
        return nodelever;
    }

    public void setNodelever(Integer nodelever) {
        this.nodelever = nodelever;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
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

    public Double getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(Double totalmoney) {
        this.totalmoney = totalmoney;
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

    public Integer getCoupontimes() {
        return coupontimes;
    }

    public void setCoupontimes(Integer coupontimes) {
        this.coupontimes = coupontimes;
    }
}