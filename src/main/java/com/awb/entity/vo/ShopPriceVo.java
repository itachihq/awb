package com.awb.entity.vo;

/**
 * Created by Administrator on 2019/7/16.
 */
public class ShopPriceVo {
    private String shopid;
    private Integer nodelever;
    private String price;
    private String shopname;


    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public Integer getNodelever() {
        return nodelever;
    }

    public void setNodelever(Integer nodelever) {
        this.nodelever = nodelever;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }
}
