package com.awb.entity.param;

/**
 * Created by Administrator on 2019/9/8.
 */
public class ShopParam extends PageParam{
    private String startTime ;

    private String endTime ;

    private String userid ;//商家id
    private String uid;//用户id
    private String shopid ;
    private Integer isselling;

    private Integer isdirectly;

    private Integer isnew;

    private Integer ord;

    private Integer ispreferential;

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public Integer getIsnew() {
        return isnew;
    }

    public void setIsnew(Integer isnew) {
        this.isnew = isnew;
    }

    public Integer getOrd() {
        return ord;
    }

    public void setOrd(Integer ord) {
        this.ord = ord;
    }

    public Integer getIspreferential() {
        return ispreferential;
    }

    public void setIspreferential(Integer ispreferential) {
        this.ispreferential = ispreferential;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
