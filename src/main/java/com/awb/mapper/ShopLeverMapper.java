package com.awb.mapper;

import com.awb.model.ShopLever;
import com.awb.model.ShopLeverExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopLeverMapper {
    int countByExample(ShopLeverExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopLever record);

    int insertSelective(ShopLever record);

    List<ShopLever> selectByExample(ShopLeverExample example);

    ShopLever selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopLever record, @Param("example") ShopLeverExample example);

    int updateByExample(@Param("record") ShopLever record, @Param("example") ShopLeverExample example);

    int updateByPrimaryKeySelective(ShopLever record);

    int updateByPrimaryKey(ShopLever record);
}