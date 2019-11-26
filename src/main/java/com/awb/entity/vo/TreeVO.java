package com.awb.entity.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/8/12.
 */
public class TreeVO {
    private String name;
    private String phone;
    private String id;
    private String  money;
    private Integer nodelever;
    private List<TreeVO> children = new ArrayList<>();

    public List<TreeVO> getChildren() {
        return children;
    }

    public void setChildren(List<TreeVO> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Integer getNodelever() {
        return nodelever;
    }

    public void setNodelever(Integer nodelever) {
        this.nodelever = nodelever;
    }
}
