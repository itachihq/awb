package com.awb.mapper;

import com.awb.model.Adminfo;
import com.awb.model.AdminfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminfoMapper {
    int countByExample(AdminfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(Adminfo record);

    int insertSelective(Adminfo record);

    List<Adminfo> selectByExample(AdminfoExample example);

    Adminfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Adminfo record, @Param("example") AdminfoExample example);

    int updateByExample(@Param("record") Adminfo record, @Param("example") AdminfoExample example);

    int updateByPrimaryKeySelective(Adminfo record);

    int updateByPrimaryKey(Adminfo record);
}