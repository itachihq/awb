package com.awb.service;

import com.awb.entity.param.InformationParam;
import com.awb.entity.vo.PageResult;
import com.awb.model.Information;

import java.util.List;

/**
 * Created by Administrator on 2019/8/22.
 */
public interface InformationService {
    PageResult<Information> listInformation(InformationParam informationParam);
    void deleteById(Integer id);
    Information selectById(Integer id);
    List<Information> listInformation();
    void updateInformation(Integer id,String content,String picture,String url);
}
