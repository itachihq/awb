package com.awb.service.impl;

import com.awb.mapper.HzhbMapper;
import com.awb.mapper.InformationMapper;
import com.awb.model.Hzhb;
import com.awb.model.HzhbExample;
import com.awb.model.Information;
import com.awb.model.InformationExample;
import com.awb.service.HzbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2019/8/22.
 */
@Service
@Transactional
public class HzbServiceImpl implements HzbService {
    @Autowired
    private HzhbMapper hzhbMapper;
    @Autowired
    private InformationMapper informationMapper;

    @Override
    public void insertHzb(Hzhb hzhb) {
        HzhbExample hzhbExample=new HzhbExample();
        hzhbExample.createCriteria().andNameEqualTo(hzhb.getName());
        List<Hzhb> hzhbList=hzhbMapper.selectByExample(hzhbExample);
        if(hzhbList.size()>0){
            throw  new  RuntimeException("合作币已存在");
        }
        hzhb.setCreatetime(new Date());
        hzhbMapper.insertSelective(hzhb);
    }

    @Override
    public void insertInformation(Information information) {
        System.out.println("len="+information.getContent().length());
        InformationExample informationExample=new InformationExample();
        informationExample.createCriteria().andTypeEqualTo(information.getType()).andTitleEqualTo(information.getTitle());
        List<Information> informationList=informationMapper.selectByExample(informationExample);
        if(informationList.size()>0){
            throw  new  RuntimeException("资讯已存在");
        }
        information.setCreatetime(new Date());
        informationMapper.insertSelective(information);
    }

    @Override
    public List<Hzhb> listHzhb() {
        HzhbExample hzhbExample=new HzhbExample();
        hzhbExample.createCriteria();
        hzhbExample.setOrderByClause("createtime desc");
        List<Hzhb> hzhbList=hzhbMapper.selectByExample(hzhbExample);
        return hzhbList;
    }

    @Override
    public void deleteHzhb(Integer id) {
        hzhbMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateHzhb(Integer id, String price, String rate) {
           Hzhb hzhb=  hzhbMapper.selectByPrimaryKey(id);
           if(null==hzhb){
               throw  new  RuntimeException("资讯不存在");
           }
           hzhb.setPrice(price);
           hzhb.setRate(rate);
           hzhbMapper.updateByPrimaryKeySelective(hzhb);
    }
}
