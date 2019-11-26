package com.awb.mapper;

import com.awb.model.Shoporder;
import com.awb.model.ShoporderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShoporderMapper {
    int countByExample(ShoporderExample example);

    int deleteByPrimaryKey(String id);

    int insert(Shoporder record);

    int insertSelective(Shoporder record);

    List<Shoporder> selectByExample(ShoporderExample example);

    Shoporder selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Shoporder record, @Param("example") ShoporderExample example);

    int updateByExample(@Param("record") Shoporder record, @Param("example") ShoporderExample example);

    int updateByPrimaryKeySelective(Shoporder record);

    int updateByPrimaryKey(Shoporder record);
}