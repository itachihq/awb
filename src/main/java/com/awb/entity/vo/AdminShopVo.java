package com.awb.entity.vo;

import java.util.Date;

/**
 * Created by Administrator on 2019/9/8.
 */
public class AdminShopVo {
    private String standard;
    private String shopPicture;
    private String shopName;
    private String information;
    private Date createtime;
    private String userid;
    private String phone;
    private String storename;
    private String id;
    private Integer isselling;
    private Integer isdirectly;
    private Integer ispreferential;
    private Integer isnew;
    private  Integer sign;
    private String shopPrice;

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    public String getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(String shopPrice) {
        this.shopPrice = shopPrice;
    }

    public Integer getIsselling() {
        return isselling;
    }

    public void setIsselling(Integer isselling) {
        this.isselling = isselling;
    }

    public Integer getIsdirectly() {
        return isdirectly;
    }

    public void setIsdirectly(Integer isdirectly) {
        this.isdirectly = isdirectly;
    }

    public Integer getIspreferential() {
        return ispreferential;
    }

    public void setIspreferential(Integer ispreferential) {
        this.ispreferential = ispreferential;
    }

    public Integer getIsnew() {
        return isnew;
    }

    public void setIsnew(Integer isnew) {
        this.isnew = isnew;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getShopPicture() {
        return shopPicture;
    }

    public void setShopPicture(String shopPicture) {
        this.shopPicture = shopPicture;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }
}
