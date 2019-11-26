package com.awb.service;

import com.awb.entity.param.PageParam;
import com.awb.entity.param.RecordParam;
import com.awb.entity.vo.*;
import com.awb.model.Deal;
import com.awb.model.Moneyrecord;
import com.awb.model.Record;

import java.util.List;

/**
 * Created by Administrator on 2019/8/23.
 */
public interface DealService {
    void agentBuy(String uid,Integer num,String phone);
    List<TransferVo> listTransfer(String uid);
    void grailSell(String uid,Integer num);
    List<Deal> listMyDeal(String uid,Integer status);
    List<DealVo>  liseDaPanDeal();
    void seil(String id,Integer num,String phone);
    void  saveCompanyDeal(String  uid, Integer num, Moneyrecord moneyrecord);
    void checkDeal(String  id,Integer status, String remark,Double realmoney,Double shouldmoney);
    List<DealRecordVo> listDealRecordSuccess();
    List<DealVo>   listBuyCompanyDeal();
    List<DealVo>   listDeal();
    void deleteGuamai(String id);
    Integer sellMax(String uid);
    PageResult<AdminRecordVo> appListShop(RecordParam recordParam);
}
