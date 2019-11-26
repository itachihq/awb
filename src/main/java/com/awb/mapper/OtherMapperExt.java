package com.awb.mapper;

import com.awb.entity.param.*;
import com.awb.entity.vo.*;
import com.awb.model.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/6/12.
 */
public interface OtherMapperExt {
    AdmuserInfoVo selectAdmuserInfo(String phone);
    AccountDetail selectLastDetail(String id);
    List<AccountVO>  selectAllAccountDetail(AccountParam accountParam);
    Account selectMyAccount(String id);
    List<ChargeOrderVo> selectAllChargeOrder(ChargeOrderParm chargeOrderParm) ;
    List<ChargeOrderVo2> selectAllChargeOrder2(ChargeOrderParm2 chargeOrderParm2) ;
    InfoVo  selectMyAom(String phone);
   List<Record> selectMyBalanceRecord(String uid);
    UserVo selectMyInfo(String uid);
    List<TransferVo> listTransfer(String uid);
    List<DealVo>  liseDaPanDeal();
    List<DealRecordVo> listDealRecordSuccess();
//    List<DealRecordVo> listDealRecordSuccess(String uid);
List<DealVo>   listBuyCompanyDeal();
    List<DealVo>   listDeal();
    List<AdminUserVo> listAllSysuser(UserParam userParam);
    List<TreeVO>  selectMyChildren(String id);
    List<TreeVO> selectByParentId(String id);
     List<Information> selectAllModule();
    List<Information> selectAllInformation(Integer type);
     Deal selectAdmNum(String userid);
     PhpVo   selectphpnews(String phone);
     AppMerchantsVo selectMerchant(String id);
    AppMerchantsVo  selectNewMerchant(String id);
     List<AppShopVo> appListShop(ShopParam shopParam);
     List<AppShopOrderVo>  selectmyorder(Map map);
    List<AdminShopVo>  listAdminShop(Map map);
    List<MerchantVo> listmerchantvo();
   List<Deal>  selectmonthdeal(String uid);
    List<AdminRecordVo>  listadminrecord(RecordParam recordParam);
    List<String> selectAllTgm();
    List<ShopOrderVo> selectAllShopOrder(ShoporderParam shoporderParam);
    List<ShopPriceVo>  selectShopPrice(String shopid);

    Shop  shopdetail(Map map);

    List<Shop>  appListNewShop(ShopParam shopParam);
    List<Shop>  appListNewShop1(ShopParam shopParam);
    //新增返利
    List<TeamVo>  selectOnerebait(String uid);
    List<TeamVo> selectTworebait(String uid);
    List<TeamVo> selectThreerebait(String uid);
    String  countPushNum(String uid);
    List<User> selectTest();
     Shop appListNewShopDetail(Map map);

     List<ShopOrderVo>  listNewShoporder(ShoporderParam shoporderParam);

     List<AdminBalanceVo> adminBalance(BalanceParm balanceParm);
    List<AdminBalanceVo> adminAdm(BalanceParm balanceParm);
     List<User> listUserArea(String address);

    List<AdminBalanceVo2> exportBalance(BalanceParm2 balanceParm);
    List<AdminBalanceVo2> exportAdm(BalanceParm2 balanceParm);
}
