package com.awb.entity.vo;

/**
 * Created by Administrator on 2019/9/9.
 */
public class MerchantVo {
    private String  id;
    private String storename;
    private  String phone;
    private String name;
    private Integer totalnum;
    private  Integer newnum;
    private Integer preferentialnum;

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

    public Integer getTotalnum() {
        return totalnum;
    }

    public void setTotalnum(Integer totalnum) {
        this.totalnum = totalnum;
    }

    public Integer getNewnum() {
        return newnum;
    }

    public void setNewnum(Integer newnum) {
        this.newnum = newnum;
    }

    public Integer getPreferentialnum() {
        return preferentialnum;
    }

    public void setPreferentialnum(Integer preferentialnum) {
        this.preferentialnum = preferentialnum;
    }
}
