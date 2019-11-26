package com.awb.service;

import com.awb.model.Hzhb;
import com.awb.model.Information;

import java.util.List;

/**
 * Created by Administrator on 2019/8/22.
 */
public interface HzbService {
    void insertHzb(Hzhb hzhb);
    void insertInformation(Information information);
    List<Hzhb> listHzhb();
    void deleteHzhb(Integer id);
    void updateHzhb(Integer id,String price,String rate);
}
