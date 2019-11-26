package com.awb.mapper;

import com.awb.model.Monthrecord;
import com.awb.model.MonthrecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MonthrecordMapper {
    int countByExample(MonthrecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(Monthrecord record);

    int insertSelective(Monthrecord record);

    List<Monthrecord> selectByExample(MonthrecordExample example);

    Monthrecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Monthrecord record, @Param("example") MonthrecordExample example);

    int updateByExample(@Param("record") Monthrecord record, @Param("example") MonthrecordExample example);

    int updateByPrimaryKeySelective(Monthrecord record);

    int updateByPrimaryKey(Monthrecord record);
}