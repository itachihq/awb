package com.awb.entity.vo;

import java.util.Date;

/**
 * Created by Administrator on 2019/9/12.
 */
public class ShopOrderVo {
    private String exepresstype;
    private String exepressnumber;
    private String id;
    private String name;
    private String shopName;
    private String phone;
    private Integer num;
    private Integer status;
    private String address;
    private String remark;
    private String totalmoney;
    private Date createtime;
    private Date updatetime;
    private Integer nodelever;
    private String storename;
    private String buyname;
    private String buyphone;
    private  Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getBuyname() {
        return buyname;
    }

    public void setBuyname(String buyname) {
        this.buyname = buyname;
    }

    public String getBuyphone() {
        return buyphone;
    }

    public void setBuyphone(String buyphone) {
        this.buyphone = buyphone;
    }

    public String getExepresstype() {
        return exepresstype;
    }

    public void setExepresstype(String exepresstype) {
        this.exepresstype = exepresstype;
    }

    public String getExepressnumber() {
        return exepressnumber;
    }

    public void setExepressnumber(String exepressnumber) {
        this.exepressnumber = exepressnumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(String totalmoney) {
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

    public Integer getNodelever() {
        return nodelever;
    }

    public void setNodelever(Integer nodelever) {
        this.nodelever = nodelever;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }
}
