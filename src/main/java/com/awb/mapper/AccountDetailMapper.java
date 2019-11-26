package com.awb.mapper;

import com.awb.model.AccountDetail;
import com.awb.model.AccountDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountDetailMapper {
    int countByExample(AccountDetailExample example);

    int deleteByPrimaryKey(String id);

    int insert(AccountDetail record);

    int insertSelective(AccountDetail record);

    List<AccountDetail> selectByExample(AccountDetailExample example);

    AccountDetail selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AccountDetail record, @Param("example") AccountDetailExample example);

    int updateByExample(@Param("record") AccountDetail record, @Param("example") AccountDetailExample example);

    int updateByPrimaryKeySelective(AccountDetail record);

    int updateByPrimaryKey(AccountDetail record);
}