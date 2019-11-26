package com.awb.entity.vo;

/**
 * Created by Administrator on 2019/9/8.
 */
public class AppMerchantsVo {
    private String id;
    private String storename;
    private Integer isnew;
    private Integer ispreferential;
    private Integer total;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public Integer getIsnew() {
        return isnew;
    }

    public void setIsnew(Integer isnew) {
        this.isnew = isnew;
    }

    public Integer getIspreferential() {
        return ispreferential;
    }

    public void setIspreferential(Integer ispreferential) {
        this.ispreferential = ispreferential;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
