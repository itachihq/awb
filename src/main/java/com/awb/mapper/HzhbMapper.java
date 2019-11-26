package com.awb.mapper;

import com.awb.model.Hzhb;
import com.awb.model.HzhbExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HzhbMapper {
    int countByExample(HzhbExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Hzhb record);

    int insertSelective(Hzhb record);

    List<Hzhb> selectByExample(HzhbExample example);

    Hzhb selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Hzhb record, @Param("example") HzhbExample example);

    int updateByExample(@Param("record") Hzhb record, @Param("example") HzhbExample example);

    int updateByPrimaryKeySelective(Hzhb record);

    int updateByPrimaryKey(Hzhb record);
}