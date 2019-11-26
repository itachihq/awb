package com.awb.service;


import com.awb.entity.param.InformationParam;
import com.awb.entity.param.UserParam;
import com.awb.entity.vo.AdminUserVo;
import com.awb.entity.vo.PageResult;
import com.awb.entity.vo.TreeVO;
import com.awb.model.Information;
import com.awb.model.User;


import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2019/8/11.
 */
public interface UserService {
    void update(String id, String remark);
  PageResult<AdminUserVo> listAllSysuser(UserParam userParam);
    void updateTradePassword(String uid, String oldTradePassword, String tradePassword);
    void updateIdentitycard(String uid,String identitycard);
    void updatePassword(String uid,String password,String code);
    void updateNodelever(String id,Integer nodelever);
    void update(String id,String remmark,String name,Integer num);
  void updateBalance(String id,String remmark,String name,Double balance);
  List<TreeVO> listTeam(String uid);
  List<TreeVO> organization(String uid);
  void updateAll(User user, Double amt, Date date, String month,String price);
  void modifyTotalmoney(String id,Double money);
  void modifyTotalnum(String id,Integer num);
  void modifyUser(String id,String storename);
  User selectOneUser(String phone);
  void update(String id);
  void updateNmae(String uid,String name);
}
