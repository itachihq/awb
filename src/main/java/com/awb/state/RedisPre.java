package com.awb.state;

/**
 * Created by asus on 2018/8/28.
 */
public enum RedisPre {

    ORDER_LOCK("order_", "提交订单锁"), URL_LOCK("lock_","全局URL锁") ,URL_AUTH("auth_","根据角色id存URL"),
    MENU("menu_","根据角色ID存取菜单"),ROLE("role_","根据用户id存取用户角色ID"),U_LEVEL("uLevel_","根据用户id存取用户等级"),
    UID("awbappuid_","根据token存取用户id"),TOKEN_LOCK("token_","频繁点击锁"),USERNAME_LOCK("username_","用户名锁");

    private String pre;
    private String description;

    RedisPre(String pre, String description) {
        this.pre = pre;
        this.description = description;
    }

    public String getPre() {
        return pre;
    }

    public void setPre(String pre) {
        this.pre = pre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
