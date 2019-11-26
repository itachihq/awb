package com.awb.service.impl;

import com.awb.entity.param.*;
import com.awb.entity.vo.*;
import com.awb.mapper.*;
import com.awb.model.*;
import com.awb.service.OtherService;
import com.awb.util.Coderutil;
import com.awb.util.DESEncrypt;
import com.awb.util.DecimalUtil;
import com.awb.util.UuidUtil;
import com.awb.util.wx.PayUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

/**
 * Created by Administrator on 2019/6/12.
 */
@Service
@Transactional
public class OtherServiceImpl implements OtherService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OtherMapperExt otherMapperExt;
    @Autowired
    private RecordMapper recordMapper;
    @Autowired
    private DealMapper dealMapper;
    @Autowired
    private ShoporderMapper shoporderMapper;
    @Autowired
    private  AdminfoMapper adminfoMapper;
    @Autowired
    private  ShopMapper shopMapper;
    @Override
    public void updatePassword(String uid, String password, String oldPassword) {
        User admuser= userMapper.selectByPrimaryKey(uid);
        if(StringUtils.isEmpty(admuser)){
            throw new RuntimeException("用户不存在");
        }
        if(!admuser.getPassword().equals(DESEncrypt.EncodePassword(oldPassword))){
            throw new RuntimeException("原密码错误");
        }
        if(password.equals(oldPassword)){
            throw new RuntimeException("新密码和原密码不能一致");
        }
        admuser.setPassword(DESEncrypt.EncodePassword(password));
        userMapper.updateByPrimaryKeySelective(admuser);
    }

    @Override
    public Map selectMyZijin(String uid) {
        User user=userMapper.selectByPrimaryKey(uid);
        if(StringUtils.isEmpty(user)){
            throw  new  RuntimeException("用户不存在");
        }
        Map map=new HashMap();
        map.put("adm",otherMapperExt.selectMyAom(user.getPhone()));
        RecordExample admrecordExample=new RecordExample();
        admrecordExample.createCriteria().andUseridEqualTo(user.getId()).andIsDeleteEqualTo(0).andDetailTypeNotEqualTo(5).andDetailTypeNotEqualTo(6).andDetailTypeNotEqualTo(7).andDetailTypeNotEqualTo(9).andDetailTypeNotEqualTo(10).andDetailTypeNotEqualTo(11).andDetailTypeNotEqualTo(17);
        admrecordExample.setOrderByClause("createtime desc");
        map.put("recrord",recordMapper.selectByExample(admrecordExample));
        return map;
    }

    @Override
    public Map selectMyBalance(String uid) {
        User user=userMapper.selectByPrimaryKey(uid);
        if(StringUtils.isEmpty(user)){
            throw  new  RuntimeException("用户不存在");
        }
        Map map=new HashMap();
        map.put("balance",user.getBalance());
        map.put("record",otherMapperExt.selectMyBalanceRecord(uid));
        return map;
    }

    @Override
    public UserVo selectMyInfo(String uid) {
        return otherMapperExt.selectMyInfo(uid);
    }

    @Override
    public PhpVo selectphpnews(String phone) {
        return otherMapperExt.selectphpnews(phone);
    }

    @Override
    public synchronized void callback(String phone, String orderno, Integer admnum, String shopname, Integer shopnum) {
    try {
        UserExample userExample=new UserExample();
        userExample.createCriteria().andPhoneEqualTo(phone);
        List<User> userList=userMapper.selectByExample(userExample);
        if(userList.size()<=0){
            throw  new  RuntimeException("用户不存在");
        }
    }catch (Exception e){
        throw  new  RuntimeException(e.getMessage());
    }
    }

    @Override
    public void updatebalance() {
        UserExample userExample=new UserExample();
        userExample.createCriteria();
        List<User> userList=userMapper.selectByExample(userExample);
        Date date=new Date();
        for(User user:userList){
            String description ="";
            description=  "余额初始化";
            Record record1=new Record();
            record1.setId(UuidUtil.get32UUID());
            record1.setAmt(user.getBalance()+"");
            record1.setBalance(user.getBalance()+"");
            record1.setNum(user.getAdmnum());
            record1.setCreatetime(date);
            record1.setUserid(user.getId());
            record1.setType(1);
            record1.setDetailType(12);
            record1.setDescription(description);
            recordMapper.insertSelective(record1);
        }
    }

    @Override
    public void updatenum() {
        UserExample userExample=new UserExample();
        userExample.createCriteria();
        List<User> userList=userMapper.selectByExample(userExample);
        Date date=new Date();
        for(User user:userList){
            String description ="";
            description=  "艾豆米初始化";
            Record record1=new Record();
            record1.setId(UuidUtil.get32UUID());
            record1.setAmt(user.getAdmnum()+"");
            record1.setBalance(user.getBalance()+"");
            record1.setNum(user.getAdmnum());
            record1.setCreatetime(date);
            record1.setUserid(user.getId());
            record1.setType(1);
            record1.setDetailType(13);
            record1.setDescription(description);
            recordMapper.insertSelective(record1);
        }
    }

    @Override
    public void importFile(MultipartFile file) {
        try{
            List<User> demoList = new ArrayList<User>();
            Workbook book = null;
//判断是xls还是xlsx
            try {
                book = new XSSFWorkbook(file.getInputStream());
            } catch (Exception ex) {
                book = new HSSFWorkbook(new POIFSFileSystem(file.getInputStream()));
            }
//获取一共有多少sheet,遍历
            int numberOfSheets = book.getNumberOfSheets();
            for (int i=0; i<numberOfSheets; i++) {
                Sheet sheet = book.getSheetAt(i);
//获取sheet中有多少行，遍历每一行
                int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
                for (int j=0;j<physicalNumberOfRows;j++){
                    if (j == 0) {
                        continue;//标题行
                    }
                    // User demo = new User();
                    Row row = sheet.getRow(j);//获得当前行数据
                    String phone=  row.getCell(0).getStringCellValue();
                    if(StringUtils.isEmpty(phone)){
                        throw  new  RuntimeException("参数错误");
                    }
                    if(phone.equals("15359162029")){
                        System.out.println(1);
                    }
                    UserExample userExample=new UserExample();
                    userExample.createCriteria().andPhoneEqualTo(phone);
                    List<User> list=userMapper.selectByExample(userExample);
                    if(list.size()==0){
                        continue;
                    }
                    User user=list.get(0);
                    Double money = row.getCell(2).getNumericCellValue();
                    int num = (int)row.getCell(3).getNumericCellValue();
                    user.setTotalnum(num);
                    user.setTotalmoney(Double.valueOf(money));
                  userMapper.updateByPrimaryKeySelective(user);
                }
            }
        }catch (Exception e){
            throw  new  RuntimeException(e.getMessage());
        }

    }

    @Override
    public String getPreid(HttpServletRequest request, String id) {
        Shoporder shopOrder=  shoporderMapper.selectByPrimaryKey(id);
        if(null==shopOrder){
            throw new RuntimeException("订单不存在");
        }
        String prepayId = "";
        try {
           Integer money=DecimalUtil.calcNumber(shopOrder.getTotalmoney(),100,"*").intValue();
           // String  no= Coderutil.getAwbOrderNo("0258");
             prepayId = PayUtil.getAppPrepayId(Coderutil.getIpAddr(request),money+"",shopOrder.getOrdernum() , null);
            // prepayId = PayUtil.getAppPrepayId(Coderutil.getIpAddr(request),"1",shopOrder.getOrdernum() , null);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            String msg=sw.toString();
            throw new RuntimeException( msg);
        }
        return  prepayId;
    }

    @Override
    public List<TeamVo> selectOnerebait(String uid) {
        User user=userMapper.selectByPrimaryKey(uid);
        if(user==null){
            throw  new  RuntimeException("用户不存在");
        }
        List<TeamVo> teamVoList=otherMapperExt.selectOnerebait(uid);
        for (TeamVo teamVo:teamVoList){
            teamVo.setPushNum(otherMapperExt.countPushNum(teamVo.getId()));
        }
        return teamVoList;
    }

    @Override
    public List<TeamVo> selectTworebait(String uid) {
        User user=userMapper.selectByPrimaryKey(uid);
        if(user==null){
            throw  new  RuntimeException("用户不存在");
        }
        List<TeamVo> teamVoList=otherMapperExt.selectTworebait(uid);
        for (TeamVo teamVo:teamVoList){
            teamVo.setPushNum(otherMapperExt.countPushNum(teamVo.getId()));
        }
        return teamVoList;
    }

    @Override
    public List<TeamVo> selectThreerebait(String uid) {
        User user=userMapper.selectByPrimaryKey(uid);
        if(user==null){
            throw  new  RuntimeException("用户不存在");
        }
        List<TeamVo> teamVoList= otherMapperExt.selectThreerebait(uid);
        for (TeamVo teamVo:teamVoList){
            teamVo.setPushNum(otherMapperExt.countPushNum(teamVo.getId()));
        }
        return teamVoList;
    }

    @Override
    public void sendadm() {
        UserExample userExample=new UserExample();
        userExample.createCriteria();
        List<User> userList=userMapper.selectByExample(userExample);
        for(User user:userList){
            String description="";
//            description="您从公司买入"+admdeal.getNum()+"枚艾豆米,并且公司赠送"+sendnum+"枚";
//            Record record=new Record();
//            record.setId(UuidUtil.get32UUID());
//            record.setUserid(user.getId());
//            record.setNum(100);
//            record.setType(1);
//            record.setDetailType(1);
//            //record.setAmt();
//            record.setBalance(admuser.getMoney()+"");
//            record.setSendnum(sendnum);
//            record.setDescription(description);
//            record.setCreatetime(date);
//            record.setDealid(admdeal.getId());
           // recordMapper.insertSelective(record);
        }
    }

    @Override
    public Adminfo selectAdminfo() {
        AdminfoExample adminfoExample=new AdminfoExample();
        adminfoExample.createCriteria();
        List<Adminfo> adminfoList=adminfoMapper.selectByExample(adminfoExample);
        if(adminfoList.size()<=0){
            throw  new  RuntimeException("信息不存在");
        }
        return adminfoList.get(0);
    }

    @Override
    public Map selectMyTiyan(String uid) {
        User user=userMapper.selectByPrimaryKey(uid);
        if(user==null){
            throw  new  RuntimeException("用户不存在");
        }
        Map map=new HashMap();
        map.put("llk",user.getPhone());
        map.put("times",user.getCoupontimes());
        return map;
    }

    @Override
    public void insertShoporderHand(String phone, Integer num, String address, String handPhone, String shopid,String name) {
                 try {
                     UserExample userExample=new UserExample();
                     userExample.createCriteria().andPhoneEqualTo(phone);
                     List<User> listUser=  userMapper.selectByExample(userExample);
                     if (listUser.size()<=0){
                         throw  new  RuntimeException("用户不存在");
                     }
                     Shop shop=    shopMapper.selectByPrimaryKey(shopid);
                     if(null==shop){
                         throw  new  RuntimeException("商品不存在");
                     }
                     User user=listUser.get(0);
                     AdminfoExample adminfoExample = new AdminfoExample();
                     adminfoExample.createCriteria();
                     List<Adminfo> adminfoList = adminfoMapper.selectByExample(adminfoExample);
                     if (adminfoList.size() <= 0) {
                         throw new RuntimeException("艾豆米信息不存在");//adminfoList.get(0).getPrice()
                     }
                     Date date = new Date();
                     Shoporder shoporder=new Shoporder();
                     String no = Coderutil.getAwbOrderNo("0256");
                     String teamMoney=DecimalUtil.calcNumber(num,399,"*").toString();
                     shoporder.setOrdernum(no);
                     shoporder.setTotalmoney(teamMoney);
                     shoporder.setAddress(address);
                     shoporder.setType(0);
                     shoporder.setName(name);
                     shoporder.setNum(num);
                     shoporder.setPhone(handPhone);
                     shoporder.setShopid(shopid);
                     shoporder.setStatus(1);
                     shoporder.setId(UuidUtil.get32UUID());
                     shoporder.setUserid(user.getId());
                     shoporder.setPrice(adminfoList.get(0).getPrice());
                     shoporder.setCreatetime(date);
                     shoporderMapper.insertSelective(shoporder);
                     int sendNum1=DecimalUtil.div2("399",shoporder.getPrice()).intValue();
                     int sendNum2=DecimalUtil.calcNumber(sendNum1,shoporder.getNum(),"*").intValue();
                     Deal deal=new Deal();
                     deal.setId(UuidUtil.get32UUID());
                     deal.setCreatetime(date);
                     deal.setUserid(user.getId());
                     deal.setPrice(shoporder.getPrice());
                     deal.setUpdatetime(date);
                     deal.setPhone("18000000001");
                     deal.setNum(sendNum2);
                     deal.setType(1);
                     deal.setStatus(1);
                     deal.setRemark("399");
                     dealMapper.insertSelective(deal);
                     String description="";
                     description="购买成功，获得"+sendNum2+"枚艾豆米";
                     Record record=new Record();
                     record.setId(UuidUtil.get32UUID());
                     record.setUserid(user.getId());
                     record.setType(1);
                     record.setDetailType(1);
                     record.setBalance(user.getMoney()+"");
                     record.setDescription(description);
                     record.setNum(sendNum2);
                     record.setCreatetime(date);
                     record.setDealid(deal.getId());
                     recordMapper.insertSelective(record);
                     int newNum=     DecimalUtil.calcNumber(user.getAdmnum(),sendNum2,"+").intValue();
                     double newMoney=DecimalUtil.calcNumber(user.getTotalmoney(),shoporder.getTotalmoney(),"+").doubleValue();
                     int sendTimes=DecimalUtil.calcNumber(shoporder.getNum(),3,"*").intValue();
                     int num1=DecimalUtil.calcNumber(user.getCoupontimes(),sendTimes,"+").intValue();
                     user.setMoney(newMoney);
                     user.setAdmnum(newNum);
                     user.setCoupontimes(num1);
                     if(0==user.getFlg()&&10==user.getNodelever()){
                         user.setFlg(1);
                     }
                     userMapper.updateByPrimaryKeySelective(user);
                     Reward(user,shoporder.getTotalmoney(),deal,date);
                 }catch (Exception e){
                     throw  new  RuntimeException(e.getMessage());
                 }

    }

    @Override
    public PageResult<ShopOrderVo> listNewShoporder(String uid,ShoporderParam shoporderParam) {
        User user=  userMapper.selectByPrimaryKey(uid);
        if(user==null){
            throw  new  RuntimeException("用户不存在");
        }
        if(StringUtils.isEmpty(shoporderParam.getAddress())){
            shoporderParam.setAddress(user.getAgentarea());
        }else {
            if(!shoporderParam.getAddress().contains(user.getAgentarea())){
                throw  new  RuntimeException("您无权限查看其他区域订单");
            }
        }

        PageHelper.startPage(shoporderParam.getCurrentPage(),shoporderParam.getPageSize());
        List<ShopOrderVo> shopList=otherMapperExt.listNewShoporder(shoporderParam);
        PageInfo<ShopOrderVo> pageInfo = new PageInfo<ShopOrderVo>(shopList);
        return new PageResult(pageInfo.getTotal(),shopList,shoporderParam.getCurrentPage()) ;
    }

    @Override
    public PageResult<AdminBalanceVo> listAdminBalance(BalanceParm balanceParm) {
        PageHelper.startPage(balanceParm.getCurrentPage(),balanceParm.getPageSize());
        List<AdminBalanceVo> list=otherMapperExt.adminBalance(balanceParm);
        PageInfo<AdminBalanceVo> pageInfo = new PageInfo<AdminBalanceVo>(list);
        return new PageResult(pageInfo.getTotal(),list,balanceParm.getCurrentPage()) ;

    }

    @Override
    public PageResult<AdminBalanceVo> listAdminAdm(BalanceParm balanceParm) {
        PageHelper.startPage(balanceParm.getCurrentPage(),balanceParm.getPageSize());
        List<AdminBalanceVo> list=otherMapperExt.adminAdm(balanceParm);
        PageInfo<AdminBalanceVo> pageInfo = new PageInfo<AdminBalanceVo>(list);
        return new PageResult(pageInfo.getTotal(),list,balanceParm.getCurrentPage()) ;
    }

    @Override
    public List<AdminBalanceVo2> exportBalance(BalanceParm2 balanceParm) {
        return otherMapperExt.exportBalance(balanceParm);
    }

    @Override
    public List<AdminBalanceVo2> exportAdm(BalanceParm2 balanceParm) {
        return otherMapperExt.exportAdm(balanceParm);
    }

    @Override
    public List<ChargeOrderVo2> exportTakemoney(ChargeOrderParm2 chargeOrderParm2) {
        return otherMapperExt.selectAllChargeOrder2(chargeOrderParm2);
    }

    public void Reward(User user,String shouldmoney,Deal deal,Date date) {
        try {
            if(StringUtils.isEmpty(user.getParentid())){
                return;
            }
            if("1".equals(user.getParentid())||"d91ed53e9200495c9112132ced069603".equals(user.getParentid())){
                return;
            }
            String description ="";
            User u1=userMapper.selectByPrimaryKey(user.getParentid());
            if(null==u1){
                return;
            }else {
                if(u1.getNodelever()==10&&u1.getFlg()!=1){

                }else {
                    Double addMoney=DecimalUtil.calcNumber(shouldmoney,0.15,"*",2).doubleValue();
                    String u1oldbalance=  u1.getBalance()+"";
                    String newBalance1=DecimalUtil.calcNumber(u1.getBalance(),addMoney,"+").toString();
                    //  String newMoney1="";//DecimalUtil.calcNumber(oldMoney2,addMoney2,"+").toString();
                    u1.setBalance(Double.valueOf(newBalance1));
                    userMapper.updateByPrimaryKeySelective(u1);
                    description=  "您的下级购买上商品，您获得一级返利"+addMoney+"元";
                    Record record1=new Record();
                    record1.setId(UuidUtil.get32UUID());
                    record1.setAmt(addMoney+"");
                    record1.setBalance(u1.getBalance()+"");
                    record1.setNum(deal.getNum());
                    record1.setCreatetime(date);
                    record1.setUserid(u1.getId());
                    record1.setDealid(deal.getId());
                    record1.setType(1);
                    record1.setDetailType(6);
                    record1.setDescription(description);
                    record1.setIsDelete(0);
                    recordMapper.insertSelective(record1);
                }

            }



            if(StringUtils.isEmpty(u1.getParentid())){
                return;
            }
            if("1".equals(u1.getParentid())||"d91ed53e9200495c9112132ced069603".equals(u1.getParentid())){
                return;
            }
            User u2=userMapper.selectByPrimaryKey(u1.getParentid());
            if(null==u2){
                return;
            }else {
                if(u2.getNodelever()==10&&u2.getFlg()!=1){
                    return;
                }
                //   String oldMoney2=u2.getTotalmoney();
                Double addMoney2=DecimalUtil.calcNumber(shouldmoney,0.05,"*",2).doubleValue();
                String u2oldbalance=  u2.getBalance()+"";
                String newBalance2=DecimalUtil.calcNumber(u2.getBalance(),addMoney2,"+").toString();
                String newMoney2="";//DecimalUtil.calcNumber(oldMoney2,addMoney2,"+").toString();
                u2.setBalance(Double.valueOf(newBalance2));
                userMapper.updateByPrimaryKeySelective(u2);
                description=  "您的下下级购买上商品，您获得二级返利"+addMoney2+"元";
                Record record2=new Record();
                record2.setId(UuidUtil.get32UUID());
                record2.setAmt(addMoney2+"");
                record2.setBalance(newBalance2);
                record2.setCreatetime(date);
                record2.setOldbalance(u2oldbalance);
                record2.setUserid(u2.getId());
                record2.setDealid(deal.getId());
                record2.setType(1);
                record2.setDetailType(7);
                record2.setDescription(description);
                recordMapper.insertSelective(record2);
            }



        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

}
