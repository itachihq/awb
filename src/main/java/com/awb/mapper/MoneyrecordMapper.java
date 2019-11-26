package com.awb.mapper;

import com.awb.model.Moneyrecord;
import com.awb.model.MoneyrecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MoneyrecordMapper {
    int countByExample(MoneyrecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(Moneyrecord record);

    int insertSelective(Moneyrecord record);

    List<Moneyrecord> selectByExample(MoneyrecordExample example);

    Moneyrecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Moneyrecord record, @Param("example") MoneyrecordExample example);

    int updateByExample(@Param("record") Moneyrecord record, @Param("example") MoneyrecordExample example);

    int updateByPrimaryKeySelective(Moneyrecord record);

    int updateByPrimaryKey(Moneyrecord record);
}