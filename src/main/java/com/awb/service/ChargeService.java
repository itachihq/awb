package com.awb.service;


import com.awb.entity.param.ChargeOrderParm;
import com.awb.entity.vo.ChargeOrderVo;
import com.awb.entity.vo.PageResult;
import com.awb.model.ChargeOrder;

import java.util.List;

/**
 * Created by Administrator on 2019/1/4.
 */
public interface ChargeService {
    void addChargeOrder(ChargeOrder chargeOrder, String uid);
    PageResult<ChargeOrderVo> listChargeOrder(ChargeOrderParm chargeOrderParm);
    void check(String id, Integer status, String message);
    List<ChargeOrder> listMyChargeOrder(String uid, Integer status);
}
