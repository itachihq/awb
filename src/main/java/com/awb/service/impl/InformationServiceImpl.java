package com.awb.service.impl;

import com.awb.entity.param.InformationParam;
import com.awb.entity.vo.PageResult;
import com.awb.mapper.InformationMapper;
import com.awb.mapper.OtherMapperExt;
import com.awb.model.Information;
import com.awb.model.InformationExample;
import com.awb.service.InformationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/8/22.
 */
@Service
public class InformationServiceImpl implements InformationService {
    @Autowired
    private InformationMapper informationMapper;
    @Autowired
    private OtherMapperExt otherMapperExt;
    @Override
    public PageResult<Information> listInformation(InformationParam informationParam) {
        PageHelper.startPage(informationParam.getCurrentPage(),informationParam.getPageSize());
        InformationExample informationExample=new InformationExample();
        informationExample.createCriteria().andTypeEqualTo(informationParam.getType());
        informationExample.setOrderByClause("createtime desc");
     List<Information> list=informationMapper.selectByExampleWithBLOBs(informationExample);//   otherMapperExt.selectAllInformation(informationParam.getType());
        PageInfo<Information> pageInfo = new PageInfo<Information>(list);
        return new PageResult(pageInfo.getTotal(),list,informationParam.getCurrentPage()) ;
    }

    @Override
    public void deleteById(Integer id) {
        informationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Information selectById(Integer id) {
        return informationMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Information> listInformation() {
        InformationExample informationExample=new InformationExample();
        informationExample.createCriteria().andTypeGreaterThan(2);
        informationExample.setOrderByClause("createtime desc");
        List<Information> list=informationMapper.selectByExampleWithBLOBs(informationExample);
        return list;
    }

    @Override
    public void updateInformation(Integer id, String content, String picture, String url) {
        Information information= informationMapper.selectByPrimaryKey(id);
        if(null==information){
            throw  new  RuntimeException("资讯不存在");
        }
        information.setUrl(url);
        information.setContent(content);
        information.setPicture(picture);
        informationMapper.updateByPrimaryKeySelective(information);
    }
}
