package com.awb.mapper;

import com.awb.model.ChargeOrder;
import com.awb.model.ChargeOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChargeOrderMapper {
    int countByExample(ChargeOrderExample example);

    int deleteByPrimaryKey(String id);

    int insert(ChargeOrder record);

    int insertSelective(ChargeOrder record);

    List<ChargeOrder> selectByExample(ChargeOrderExample example);

    ChargeOrder selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ChargeOrder record, @Param("example") ChargeOrderExample example);

    int updateByExample(@Param("record") ChargeOrder record, @Param("example") ChargeOrderExample example);

    int updateByPrimaryKeySelective(ChargeOrder record);

    int updateByPrimaryKey(ChargeOrder record);
}