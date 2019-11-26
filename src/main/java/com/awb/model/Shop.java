package com.awb.model;

import java.util.Date;

public class Shop {
    private String id;

    private String shopName;

    private String shopPrice;

    private String shopPicture;

    private String userid;

    private String information;

    private String detai;

    private String standard;

    private Integer isDelete;

    private Integer num;

    private Integer isselling;

    private Integer isdirectly;

    private Integer isnew;

    private Integer ord;

    private Integer sign;

    private Integer ispreferential;

    private String postage;

    private String remark;

    private Date createtime;

    private Date updatetime;

    private Integer admnum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(String shopPrice) {
        this.shopPrice = shopPrice == null ? null : shopPrice.trim();
    }

    public String getShopPicture() {
        return shopPicture;
    }

    public void setShopPicture(String shopPicture) {
        this.shopPicture = shopPicture == null ? null : shopPicture.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information == null ? null : information.trim();
    }

    public String getDetai() {
        return detai;
    }

    public void setDetai(String detai) {
        this.detai = detai == null ? null : detai.trim();
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard == null ? null : standard.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    public Integer getIspreferential() {
        return ispreferential;
    }

    public void setIspreferential(Integer ispreferential) {
        this.ispreferential = ispreferential;
    }

    public String getPostage() {
        return postage;
    }

    public void setPostage(String postage) {
        this.postage = postage == null ? null : postage.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public Integer getAdmnum() {
        return admnum;
    }

    public void setAdmnum(Integer admnum) {
        this.admnum = admnum;
    }
}