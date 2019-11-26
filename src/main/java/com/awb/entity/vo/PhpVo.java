package com.awb.entity.vo;

/**
 * Created by Administrator on 2019/9/5.
 */
public class PhpVo {
    private String phone;
    private String name;
    private Integer admnum;
    private Double price;
  //  private String tgm;
    private Integer nodelever;

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

    public Integer getAdmnum() {
        return admnum;
    }

    public void setAdmnum(Integer admnum) {
        this.admnum = admnum;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }



    public Integer getNodelever() {
        return nodelever;
    }

    public void setNodelever(Integer nodelever) {
        this.nodelever = nodelever;
    }
}
