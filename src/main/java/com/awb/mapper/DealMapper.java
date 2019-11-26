package com.awb.mapper;

import com.awb.model.Deal;
import com.awb.model.DealExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DealMapper {
    int countByExample(DealExample example);

    int deleteByPrimaryKey(String id);

    int insert(Deal record);

    int insertSelective(Deal record);

    List<Deal> selectByExample(DealExample example);

    Deal selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Deal record, @Param("example") DealExample example);

    int updateByExample(@Param("record") Deal record, @Param("example") DealExample example);

    int updateByPrimaryKeySelective(Deal record);

    int updateByPrimaryKey(Deal record);
}