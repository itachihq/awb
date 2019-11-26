package com.awb.mapper;

import com.awb.model.Information;
import com.awb.model.InformationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InformationMapper {
    int countByExample(InformationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Information record);

    int insertSelective(Information record);

    List<Information> selectByExampleWithBLOBs(InformationExample example);

    List<Information> selectByExample(InformationExample example);

    Information selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Information record, @Param("example") InformationExample example);

    int updateByExampleWithBLOBs(@Param("record") Information record, @Param("example") InformationExample example);

    int updateByExample(@Param("record") Information record, @Param("example") InformationExample example);

    int updateByPrimaryKeySelective(Information record);

    int updateByPrimaryKeyWithBLOBs(Information record);

    int updateByPrimaryKey(Information record);
}