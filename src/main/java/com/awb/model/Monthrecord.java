package com.awb.model;

import java.util.Date;

public class Monthrecord {
    private String id;

    private String userid;

    private String personmoney;

    private String teammoney;

    private String amt;

    private Date createtime;

    private Integer isDelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getPersonmoney() {
        return personmoney;
    }

    public void setPersonmoney(String personmoney) {
        this.personmoney = personmoney == null ? null : personmoney.trim();
    }

    public String getTeammoney() {
        return teammoney;
    }

    public void setTeammoney(String teammoney) {
        this.teammoney = teammoney == null ? null : teammoney.trim();
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt == null ? null : amt.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}