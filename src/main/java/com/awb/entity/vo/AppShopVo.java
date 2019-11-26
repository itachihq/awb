package com.awb.entity.vo;

/**
 * Created by Administrator on 2019/9/8.
 */
public class AppShopVo {
    private String id;
    private String shopName;
    private String shopPrice;
    private String shopPicture;
    private String userid;
    private String information;
    private String standard;
    private Integer price;
    private String averageprice;
    private Integer isdirectly;
    private Integer isselling;
    private Integer admnum;

    public Integer getAdmnum() {
        return admnum;
    }

    public void setAdmnum(Integer admnum) {
        this.admnum = admnum;
    }

    public Integer getIsdirectly() {
        return isdirectly;
    }

    public void setIsdirectly(Integer isdirectly) {
        this.isdirectly = isdirectly;
    }

    public Integer getIsselling() {
        return isselling;
    }

    public void setIsselling(Integer isselling) {
        this.isselling = isselling;
    }

    public String getAverageprice() {
        return averageprice;
    }

    public void setAverageprice(String averageprice) {
        this.averageprice = averageprice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(String shopPrice) {
        this.shopPrice = shopPrice;
    }

    public String getShopPicture() {
        return shopPicture;
    }

    public void setShopPicture(String shopPicture) {
        this.shopPicture = shopPicture;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
