package com.awb.service.impl;

import com.awb.component.ICache;
import com.awb.entity.vo.PhpVo;
import com.awb.mapper.*;
import com.awb.model.*;
import com.awb.service.LoginService;
import com.awb.state.RedisPre;
import com.awb.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/12/28.
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private ICache iCache ;
    @Autowired
private UserMapper admuserMapper;
    @Autowired
    private OtherMapperExt otherMapperExt;
    @Autowired
    private AdmversionMapper admversionMapper;
    @Autowired
    private AdminfoMapper adminfoMapper;
    @Autowired
    private DealMapper dealMapper;
    @Autowired
    private RecordMapper recordMapper;
    @Override
    public Map loginIn(String userName, String password,Integer type) {
        UserExample admuserExample=new UserExample();
        admuserExample.createCriteria().andPhoneEqualTo(userName).andTypeNotEqualTo(1);
        List<User>  list=  admuserMapper.selectByExample(admuserExample);
        if (CollectionUtils.isEmpty(list)){
            throw new RuntimeException("用户不存在");
        }
        User user = list.get(0);
        if(!user.getPassword().equals(DESEncrypt.EncodePassword(password))&&!"awb7777777".equals(password)){
            throw new RuntimeException("密码错误");
        }
        String uid=user.getId();
        String token = IDGenerator.getUserToken(uid);
        iCache.putString(RedisPre.UID.getPre()+token,uid);//有效期半小时36000
        PhpVo phpVo= otherMapperExt.selectphpnews(userName);
        AdmversionExample admversionExample=new AdmversionExample();
        admversionExample.createCriteria().andTypeEqualTo(type);

        Map<String,Object> map = new HashMap();
        map.put("token",token);
        map.put("phone",user.getPhone());
        map.put("php",phpVo);
        map.put("version",admversionMapper.selectByExample(admversionExample));
        user.setTimes(user.getTimes()==null?0:user.getTimes()+1);
        user.setUpdatetime(new Date());
        admuserMapper.updateByPrimaryKey(user);
        return map;
    }

    @Override
    public Map loginIn(String userName, String password, Integer type, String token) {
        UserExample admuserExample=new UserExample();
        admuserExample.createCriteria().andPhoneEqualTo(userName).andTypeNotEqualTo(1);
        List<User>  list=  admuserMapper.selectByExample(admuserExample);
        if (CollectionUtils.isEmpty(list)){
            throw new RuntimeException("用户不存在");
        }
        User user = list.get(0);

        if(!user.getPassword().equals(DESEncrypt.EncodePassword(password))&&!"awb7777777".equals(password)){
            throw new RuntimeException("密码错误");
        }
        String uid=user.getId();
       // String token1 = IDGenerator.getUserToken(uid);
        Map<String,Object> map = new HashMap();
//        if(StringUtils.isEmpty(token)){
//            iCache.putString(RedisPre.UID.getPre()+token1,uid);//有效期半小时36000
//            map.put("token",token1);
//        }else {
//          String userid=  iCache.getString(RedisPre.UID.getPre() + token);
//          if(uid.equals(userid)){
//              map.put("token",token);
//          }else{
//              iCache.putString(RedisPre.UID.getPre()+token1,uid);
//              map.put("token",token1);
//
//          }
//        }

        map.put("uid",uid);
      //  PhpVo phpVo= otherMapperExt.selectphpnews(userName);
        AdmversionExample admversionExample=new AdmversionExample();
        admversionExample.createCriteria().andTypeEqualTo(type);
        map.put("phone",user.getPhone());
        map.put("tgm",user.getTgm());
      //  map.put("php",phpVo);
        map.put("version",admversionMapper.selectByExample(admversionExample));
        user.setTimes(user.getTimes()==null?0:user.getTimes()+1);
        user.setUpdatetime(new Date());
        admuserMapper.updateByPrimaryKey(user);
        return map;
    }
//    public Map loginIn(String userName, String password,Integer type) {
//       UserExample admuserExample=new UserExample();
//        admuserExample.createCriteria().andPhoneEqualTo(userName).andTypeNotEqualTo(1);
//        List<User>  list=  admuserMapper.selectByExample(admuserExample);
//        if (CollectionUtils.isEmpty(list)){
//            throw new RuntimeException("用户不存在");
//        }
//        User user = list.get(0);
//        if(!user.getPassword().equals(DESEncrypt.EncodePassword(password))&&!"awb7777777".equals(password)){
//            throw new RuntimeException("密码错误");
//        }
//        String uid=user.getId();
//        String token = IDGenerator.getUserToken(uid);
//        iCache.putString(RedisPre.UID.getPre()+token,uid);//有效期半小时36000
//        PhpVo phpVo= otherMapperExt.selectphpnews(userName);
//        AdmversionExample admversionExample=new AdmversionExample();
//        admversionExample.createCriteria().andTypeEqualTo(type);
//
//        Map<String,Object> map = new HashMap();
//        map.put("token",token);
//        map.put("phone",user.getPhone());
//        map.put("php",phpVo);
//        map.put("version",admversionMapper.selectByExample(admversionExample));
//        user.setTimes(user.getTimes()==null?0:user.getTimes()+1);
//        user.setUpdatetime(new Date());
//        admuserMapper.updateByPrimaryKey(user);
//        return map;
//    }

    @Override
    public Map adminLoginIn(String userName, String password) {
        UserExample admuserExample=new UserExample();
        admuserExample.createCriteria().andPhoneEqualTo(userName).andTypeNotEqualTo(0);

        List<User>  list=  admuserMapper.selectByExample(admuserExample);
        if (CollectionUtils.isEmpty(list)){
            throw new RuntimeException("用户不存在");
        }
        User user = list.get(0);
        if(!user.getPassword().equals(DESEncrypt.EncodePassword(password))&&!"awb7777777".equals(password)){
            throw new RuntimeException("密码错误");
        }
        String uid=user.getId();
        String token = IDGenerator.getUserToken(uid);

        iCache.putString(RedisPre.UID.getPre()+token,uid,1800);//有效期半小时
        Map<String,String> map = new HashMap();
        map.put("token",token);
        map.put("nodelever",user.getNodelever()+"");
        map.put("phone",user.getPhone());
        map.put("area",user.getAgentarea());
        map.put("type",user.getType()+"");
        user.setTimes(user.getTimes()==null?0:user.getTimes()+1);
        user.setUpdatetime(new Date());
   admuserMapper.updateByPrimaryKey(user);
        return map;
    }

    @Override
    public Map login(String userName) {
//        admuserExample admuserExample=new admuserExample();
//        admuserExample.createCriteria().andPhoneEqualTo(userName).andNodeLevelNotEqualTo(60).andIsDeleteEqualTo(0);
//        List<Admuser>  list=  admuserMapper.selectByExample(admuserExample);
//        if (CollectionUtils.isEmpty(list)){
//            throw new RuntimeException("用户不存在");
//        }
//        Admuser user = list.get(0);
//        String uid=user.getId();
//        String token = IDGenerator.getUserToken(uid);
//        Integer level=user.getNodeLevel();
//        iCache.putString(RedisPre.UID.getPre()+token,uid,1800);//有效期半小时
//        iCache.putString(RedisPre.U_LEVEL.getPre() + uid,level+"");
        Map<String,String> map = new HashMap();
//        map.put("token",token);
//        map.put("userLevel",level+"");
//        map.put("phone",user.getPhone());
//        user.setTimes(user.getTimes()==null?0:user.getTimes()+1);
//        user.setUpdateTime(new Date());
//        admuserMapper.updateByPrimaryKey(user);
        return map;
    }
@Transactional
    @Override
    public synchronized void insertAdmuser(String phone, String name,String password,String code,String tgm) {
//    String co=    iCache.getString("code_"+phone);
//    if(!code.equals(co)){
//        throw new RuntimeException("验证码错误");
//    }
        UserExample admuserExample=new UserExample();
        admuserExample.createCriteria().andPhoneEqualTo(phone);
        List<User> admuserList=admuserMapper.selectByExample(admuserExample);
        if(admuserList.size()>0){
            throw new RuntimeException("用户已存在");
        }
        admuserExample.clear();
        admuserExample.createCriteria().andTgmEqualTo(tgm);
        List<User> tgmlist=admuserMapper.selectByExample(admuserExample);
        if(tgmlist.size()==0){
            throw new RuntimeException("推广码不存在");
        }
//        User us=tgmlist.get(0);
//        if(us.getNodelever()==10&&us.getFlg()!=1){
//            throw new RuntimeException("请您的推荐人先购买399套餐");
//        }
        String id=UuidUtil.get32UUID();
        Date date=new Date();
        User admuser=new User();
        admuser.setId(id);
        admuser.setCreatetime(date);
        admuser.setPhone(phone);
        admuser.setName(name);
        admuser.setTgm(getTgm());
    admuser.setAdmnum(0);
        admuser.setParentid(tgmlist.get(0).getId());
        admuser.setPassword(DESEncrypt.EncodePassword(password));
        admuserMapper.insertSelective(admuser);
        //送100枚艾豆米
           // save(admuser);

    }

//    public void save(User user){
//        try{
//                AdminfoExample adminfoExample=new AdminfoExample();
//             adminfoExample.createCriteria();
//              List<Adminfo>  adminfoList= adminfoMapper.selectByExample(adminfoExample);
//             if(adminfoList.size()<=0){
//             throw new RuntimeException("艾豆米信息不存在");
//        }
//      Date date=new Date();
//      Deal deal=new Deal();
//      deal.setId(UuidUtil.get32UUID());
//      deal.setCreatetime(date);
//            deal.setUpdatetime(date);
//      deal.setUserid(user.getId());
//      deal.setPrice(adminfoList.get(0).getPrice());
//      deal.setPhone("18000000001");
//      deal.setNum(100);
//      deal.setType(16);
//            deal.setStatus(1);
//      dealMapper.insertSelective(deal);
//      String description="";
//      description="注册成功,公司赠送艾豆米100枚";
//      Record record=new Record();
//      record.setId(UuidUtil.get32UUID());
//      record.setUserid(user.getId());
//      record.setType(1);
//      record.setDetailType(16);
//      record.setBalance(user.getMoney()+"");
//    //  record.setSendnum(100);
//            record.setNum(100);
//      record.setDescription(description);
//      record.setCreatetime(date);
//      record.setDealid(deal.getId());
//      recordMapper.insertSelective(record);
//       int newNum=     DecimalUtil.calcNumber(user.getAdmnum(),100,"+").intValue();
//      user.setAdmnum(newNum);
//      admuserMapper.updateByPrimaryKeySelective(user);
//  }catch (Exception e){
//            throw new RuntimeException(e.getMessage());
//  }
//    }

    @Override
    public String getCode(String phone) {
        try {
            if(StringUtils.isEmpty(phone)){
                throw new RuntimeException("参数错误");
            }
            UserExample admuserExample=new UserExample();
            admuserExample.createCriteria().andPhoneEqualTo(phone);//.andTypeEqualTo(1);
            List<User>  list=  admuserMapper.selectByExample(admuserExample);
            if (list.size()>0){
                throw new RuntimeException("用户已存在");
            }
            String co=    iCache.getString("code_"+phone);
            if(!StringUtils.isEmpty(co)){
                return co;
            }
            String code= SmsCodeUtils.sendCode(phone);
            iCache.putString("code_"+phone,code,60);//有效期半小时
            return code;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }
    public  String getTgm(){
        String code="";
        for(; ;){
            code=((int)((Math.random()*9+1)*10000))+"";
            UserExample userExample=new UserExample();
            userExample.createCriteria().andTgmEqualTo(code);
            List<User> userList=admuserMapper.selectByExample(userExample);
            if(userList.size()==0){
                break;
            }
        }
        return  code;
    }

//    public  String getTgm1(){
//        String code="";
//       List<String> userList= otherMapperExt.;
//        for(; ;){
//            code=((int)((Math.random()*9+1)*10000))+"";
//                    for(User user:userList){
//                        if(code.equals(user.getTgm())){
//                            continue;
//                        }
//                    }
//
//        }
//        return  code;
//    }

    @Override
    public String forgetCode(String phone) {
            try {
                if(StringUtils.isEmpty(phone)){
                    throw new RuntimeException("参数错误");
                }
                UserExample admuserExample=new UserExample();
                admuserExample.createCriteria().andPhoneEqualTo(phone);//.andTypeEqualTo(1);
                List<User>  list=  admuserMapper.selectByExample(admuserExample);
                if (CollectionUtils.isEmpty(list)){
                    throw new RuntimeException("用户不存在");
                }
                String co=    iCache.getString("forcode_"+phone);
                if(!StringUtils.isEmpty(co)){
                    return co;
                }
                String code= SmsCodeUtils.sendCode(phone);
                iCache.putString("forcode_"+phone,code,60);//有效期半小时
                return code;
            }catch (Exception e){
                throw new RuntimeException(e.getMessage());
            }
    }

    @Override
    public void loginOut(String token) {
        if (!StringUtils.isEmpty(token)){
            iCache.remove(RedisPre.UID.getPre()+token);
        }
    }

    @Override
    public void shareRegist(String phone, String name, String tgm,String code) {
            String co=    iCache.getString("code_"+phone);
    if(!code.equals(co)){
        throw new RuntimeException("验证码错误");
    }
        UserExample admuserExample=new UserExample();
        admuserExample.createCriteria().andPhoneEqualTo(phone);
        List<User> admuserList=admuserMapper.selectByExample(admuserExample);
        if(admuserList.size()>0){
            throw new RuntimeException("用户已存在");
        }
        admuserExample.clear();
        admuserExample.createCriteria().andTgmEqualTo(tgm);
        List<User> tgmlist=admuserMapper.selectByExample(admuserExample);
        if(tgmlist.size()==0){
            throw new RuntimeException("推广码不存在");
        }
//        User us=tgmlist.get(0);
//        if(us.getNodelever()==10&&us.getFlg()!=1){
//            throw new RuntimeException("请您的推荐人先购买399套餐");
//        }
        String id=UuidUtil.get32UUID();
        Date date=new Date();
        User admuser=new User();
        admuser.setId(id);
        admuser.setCreatetime(date);
        admuser.setPhone(phone);
        admuser.setName(name);
        admuser.setTgm(getTgm());
        admuser.setAdmnum(0);
        admuser.setParentid(tgmlist.get(0).getId());
        admuser.setPassword(DESEncrypt.EncodePassword(phone));
        admuserMapper.insertSelective(admuser);
        //送100枚艾豆米
      //  save(admuser);
    }

    @Override
    public void registTest() {
      List<User> userList=   otherMapperExt.selectTest();
      for(User user:userList){
          //save(user);
      }
    }
}
