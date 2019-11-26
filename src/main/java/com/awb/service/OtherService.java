package com.awb.service;

import com.awb.entity.param.*;
import com.awb.entity.vo.*;
import com.awb.model.Adminfo;
import com.awb.model.Shoporder;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/6/12.
 */
public interface OtherService {
    void updatePassword(String uid,String password,String oldPassword);
    Map selectMyZijin(String  uid);
    Map selectMyBalance(String uid);
    UserVo selectMyInfo(String uid);
    PhpVo   selectphpnews(String phone);
    void callback(String  phone,String orderno,Integer admnum,String shopname,Integer shopnum);
    void updatebalance();
    void updatenum();
    void importFile(MultipartFile file);
    String getPreid(HttpServletRequest request, String id);

    List<TeamVo>  selectOnerebait(String uid);
    List<TeamVo> selectTworebait(String uid);//selectThreerebait
    List<TeamVo>  selectThreerebait(String uid);
    //
    void sendadm();
    Adminfo selectAdminfo();
    Map selectMyTiyan(String uid);
    void insertShoporderHand(String phone,Integer num,String  address,String handPhone,String shopid,String name);

    PageResult<ShopOrderVo> listNewShoporder(String uid,ShoporderParam shoporderParam);

    PageResult<AdminBalanceVo> listAdminBalance(BalanceParm balanceParm);
    PageResult<AdminBalanceVo> listAdminAdm(BalanceParm balanceParm);

    List<AdminBalanceVo2> exportBalance(BalanceParm2 balanceParm);
    List<AdminBalanceVo2> exportAdm(BalanceParm2 balanceParm);
    List<ChargeOrderVo2> exportTakemoney(ChargeOrderParm2 chargeOrderParm2) ;

}
