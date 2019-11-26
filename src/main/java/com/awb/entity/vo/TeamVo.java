package com.awb.entity.vo;

import java.util.Date;

/**
 * Created by Administrator on 2019/10/21.
 */
public class TeamVo {
    private  String id;
    private String phone;
    private String name;
    private Integer ordernum;
    private String consumMoney;
    private String pushNum;
    private Date createtime;
    private Integer nodelever;

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

    public Integer getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public String getConsumMoney() {
        return consumMoney;
    }

    public void setConsumMoney(String consumMoney) {
        this.consumMoney = consumMoney;
    }

    public String getPushNum() {
        return pushNum;
    }

    public void setPushNum(String pushNum) {
        this.pushNum = pushNum;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getNodelever() {
        return nodelever;
    }

    public void setNodelever(Integer nodelever) {
        this.nodelever = nodelever;
    }
}
