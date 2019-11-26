package com.awb.mapper;

import com.awb.model.Phpcallback;
import com.awb.model.PhpcallbackExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PhpcallbackMapper {
    int countByExample(PhpcallbackExample example);

    int deleteByPrimaryKey(String id);

    int insert(Phpcallback record);

    int insertSelective(Phpcallback record);

    List<Phpcallback> selectByExample(PhpcallbackExample example);

    Phpcallback selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Phpcallback record, @Param("example") PhpcallbackExample example);

    int updateByExample(@Param("record") Phpcallback record, @Param("example") PhpcallbackExample example);

    int updateByPrimaryKeySelective(Phpcallback record);

    int updateByPrimaryKey(Phpcallback record);
}