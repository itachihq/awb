package com.awb.mapper;

import com.awb.model.Admversion;
import com.awb.model.AdmversionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdmversionMapper {
    int countByExample(AdmversionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admversion record);

    int insertSelective(Admversion record);

    List<Admversion> selectByExample(AdmversionExample example);

    Admversion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Admversion record, @Param("example") AdmversionExample example);

    int updateByExample(@Param("record") Admversion record, @Param("example") AdmversionExample example);

    int updateByPrimaryKeySelective(Admversion record);

    int updateByPrimaryKey(Admversion record);
}