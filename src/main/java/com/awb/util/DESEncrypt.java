package com.awb.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**DES加密工具类
 * @author qwy
 *
 * @createTime 2015-04-17 01:03:49
 */
public class DESEncrypt {

	private static Logger log = LoggerFactory.getLogger(DESEncrypt.class);
	
	/**
	 * 加密
	 * @param src 要加密的数据
	 * @param key 加密取用的key。八位字符串
	 * @return
	 * @throws Exception
	 */
	public static final String encrypt(String src, String key)throws Exception {
		if(src==null)
			return null;
		if("".equals(src))
			return "";
		SecureRandom sr = new SecureRandom(); 
		
		DESKeySpec dks = new DESKeySpec(key.getBytes()); 
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES"); 

        SecretKey securekey = keyFactory.generateSecret(dks); 
        
        Cipher cipher = Cipher.getInstance("DES"); 

        // 用密匙初始化Cipher对象 

        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr); 
        byte bb[] = cipher.doFinal(src.getBytes());
        StringBuffer buff = new StringBuffer(bb.length);
		String sTemp;
		for(int i=0;i<bb.length;i++){
				sTemp = Integer.toHexString(0xFF &bb[i]);
				if(sTemp.length()<2){
					buff.append(0);
				}
				
				buff.append(sTemp.toUpperCase());
			
		}
        
        return buff.toString();
        
	}
	
	/**
	 * 解密
	 * @param src 要解密的数据源
	 * @param key 加密时取用的key，八位字符串
	 * @return
	 * @throws Exception
	 */
	public static final String decrypt(String src, String key)throws Exception { 
		if(src==null)
			return null;
		if("".equals(src))
			return "";
		try {
			int len = (src.length()/2);
			byte [] result = new byte[len];
			char[] achar = src.toString().toCharArray();
			for(int j=0;j<len;j++){
				int pos = j*2;
				result[j]= ((byte)(toByte(achar[pos])<<4|toByte(achar[pos+1])));
			}
			
			
			// DES算法要求有一个可信任的随机数源 

			SecureRandom sr = new SecureRandom(); 

			// 从原始密匙数据创建一个DESKeySpec对象 

			DESKeySpec dks = new DESKeySpec(key.getBytes()); 

			// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成 

			// 一个SecretKey对象 

			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES"); 

			SecretKey securekey = keyFactory.generateSecret(dks); 

			// Cipher对象实际完成解密操作 

			Cipher cipher = Cipher.getInstance("DES"); 

			// 用密匙初始化Cipher对象 

			cipher.init(Cipher.DECRYPT_MODE, securekey, sr); 

			// 现在，获取数据并解密 

			// 正式执行解密操作 

			return new String(cipher.doFinal(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("操作异常: ",e);
		} 
		return src;

     } 
	
	private static byte toByte(char c){
		
		byte  b = (byte)"0123456789ABCDEF".indexOf(c);
		return b;
	}
	
	/**加密字符串;<br>
	 * 登录密码;支付密码;
	 * @param string 需要加密的字符串;
	 * @return 加密过后的字符串;
	 * @throws Exception
	 */
	public static String EncodePassword(String string){
		try {
			String str = encrypt(string,"bym*_*@@");
			String str2 = encrypt(str,"baiyimao");
			return str2;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("操作异常: ",e);
		}
		return null;
	}
	
	/**解密字符串;<br>
	 * 登录密码;支付密码;
	 * @param string 加密过后的字符串;
	 * @return 解密过后的字符串;
	 * @throws Exception
	 */
	public static String DecodePassword(String string){
		String de2=null;
		try {
			String de = decrypt(string, "baiyimao");
			de2 = decrypt(de, "bym*_*@@");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("操作异常: ",e);
		}
		return de2;
	}
	
	/**加密字符串;<br>
	 * 帐号,手机号码,邮箱;
	 * @param string 需要加密的字符串;
	 * @return 加密过后的字符串;
	 * @throws Exception
	 */
	public static String EncodeUsername(String string){
		try {
			String str = encrypt(string,"userName");
			String str2 = encrypt(str,"bym-_-@@");
			return str2;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("操作异常: ",e);
		}
		return null;
	}
	
	
	/**解密字符串;<br>
	 * 帐号,手机号码,邮箱;
	 * @param string 加密过后的字符串;
	 * @return 解密过后的字符串;
	 * @throws Exception
	 */
	public static String DecodeUsername(String string){
		String de2=null;
		try {
			String de = decrypt(string, "bym-_-@@");
			de2 = decrypt(de, "userName");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("操作异常: ",e);
		}
		return de2;
	}
	
	
	/**加密字符串;<br>
	 * 身份证号
	 * @param string 需要加密的字符串;
	 * @return 加密过后的字符串;
	 * @throws Exception
	 */
	public static String EncodeIdCard(String string){
		try {
			String str = encrypt(string,"@*IdCard*");
			String str2 = encrypt(str,"bymCards");
			return str2;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("操作异常: ",e);
		}
		return null;
	}
	
	
	/**解密字符串;<br>
	 * 身份证号
	 * @param string 加密过后的字符串;
	 * @return 解密过后的字符串;
	 * @throws Exception
	 */
	public static String DecodeIdCard(String string){
		String de2=null;
		try {
			String de = decrypt(string, "bymCards");
			de2 = decrypt(de, "@*IdCard*");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("操作异常: ",e);
		}
		return de2;
	}
	
	
	
	/**加密字符串;<br>
	 * 银行卡
	 * @param string 需要加密的字符串;
	 * @return 加密过后的字符串;
	 * @throws Exception
	 */
	public static String EncodeBankCard(String string){
		try {
			String str = encrypt(string,"bymBanks");
			String str2 = encrypt(str,"$_$_bank");
			return str2;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("操作异常: ",e);
		}
		return null;
	}
	
	
	/**解密字符串;<br>
	 * 银行卡
	 * @param string 加密过后的字符串;
	 * @return 解密过后的字符串;
	 * @throws Exception
	 */
	public static String DecodeBankCard(String string){
		String de2=null;
		try {
			String de = decrypt(string, "$_$_bank");
			de2 = decrypt(de, "bymBanks");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("操作异常: ",e);
		}
		return de2;
	}
	
	/**加密字符串;<br>
	 * 数据库jdbc加密;
	 * @param string 需要加密的字符串;
	 * @return 加密过后的字符串;
	 * @throws Exception
	 */
	public static String EncodeProperties(String string){
		try {
			String str = encrypt(string,"properties");
			String str2 = encrypt(str,"baiyimao");
			return str2;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("操作异常: ",e);
		}
		return null;
	}
	
	/**解密字符串;<br>
	 * 数据库jdbc;
	 * @param string 加密过后的字符串;
	 * @return 解密过后的字符串;
	 * @throws Exception
	 */
	public static String DecodeProperties(String string){
		String de2=null;
		try {
			String de = decrypt(string, "baiyimao");
			de2 = decrypt(de, "properties");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("操作异常: ",e);
		}
		return de2;
	}
	
	/**加密字符串;<br>
	 * 登录密码;支付密码;
	 * @param string 需要加密的字符串;
	 * @return 加密过后的字符串;
	 * @throws Exception
	 */
	public static String EncodeToken(String string){
		try {
			String str = encrypt(string,"tkn*_*@@");
			return str;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("操作异常: ",e);
		}
		return null;
	}
	
	
	/**解密字符串;<br>
	 * 登录密码;支付密码;
	 * @param string 加密过后的字符串;
	 * @return 解密过后的字符串;
	 * @throws Exception
	 */
	public static String DecodeToken(String string){
		String de2=null;
		try {
			de2 = decrypt(string, "tkn*_*@@");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("操作异常: ",e);
		}
		return de2;
	}
	
	public static void main(String[] args) throws Exception {
		//ApplicationContext context = ApplicationContexts.getContexts();
//		String username = "adminss";
//		String phone = "18025729778";
//		String password = "123456";
//		String idCard = "441723199302145632";
//		String bankCard = "6228481164799729911";
//		System.out.println("------------------加密---------------");
/*	String jUsername = DESEncrypt.EncodeUsername("15112304365");
	String jpassword = DESEncrypt.EncodePassword("Admin.Huoq.Com");
	System.out.println(jUsername);
	System.out.println(jpassword);*/
//		String jphone = DESEncrypt.EncodeUsername(phone);
		//String jpassword = DESEncrypt.EncodePassword("123");
//		String jidCard = DESEncrypt.EncodeIdCard(idCard);
//		String jbankCard = DESEncrypt.EncodeBankCard(bankCard);
//		
//		System.out.println(jUsername+"__"+jUsername.length());
//		System.out.println(jphone+"__"+jphone.length());
//		System.out.println(jpassword+"__"+jpassword.length());
//		System.out.println(jidCard+"__"+jidCard.length());
//		System.out.println(jbankCard+"__"+jbankCard.length());
//		System.out.println("------------------解密1---------------");
		//System.out.println(DESEncrypt.DecodeUsername("7D679013BFDA64A75A2BB0C16236E3F7159656B66BC72EBF1DFADA94F5B577092A1C0F45F85B0DE8"));
	    //System.out.println(DESEncrypt.DecodeBankCard("A78149C22CDF013858CE055C44EDDF4F31E66C09C5C68D35B50238B72EC2ACF94C6B0442E3A9ADD56208293ACC41615E240F7A7D9F471BF0"));
	   // System.out.println(DecodeIdCard("C0E122AA57775B59A97BDBD3E2E244397E0B19C0B6D756D48E319BE4614A5734F44B6AED874E659FBC12785CCD61A2FAFE07908B19B73E96"));
		//System.out.println(DESEncrypt.DecodeUsername("D622B34ADF4413A30B928482E5C50E7CEBDE58178BF07C7792C8172AFBBC55B22A1C0F45F85B0DE8"));
		//System.out.println(DESEncrypt.EncodeUsername("15229062116"));
		//System.out.println(DESEncrypt.DecodePassword("38C763DB5E8B61CEB3D0CBA6AC2F3C7DBD3ADF7524BD918FB9CF76A913F549AF42599D8BA4A8EA49"));
		//System.out.println(DESEncrypt.DecodeToken("D09E0FAA7BFDC2B4CB3A7DB8BC41A89AB7A5E5B7473C5FFEA75190434A2D0947A62C76E5BE8A70426F7FE95EAFC6E9ABAA3F07017C8F267E"));
		/*System.out.println(DESEncrypt.DecodeIdCard("43B6CEEAF155DB0A9E09A7FCB6C1FF0FBE803134BABD15087E7AEC25B52EF8625FF50FDC12E3A378A97709650597C709FE07908B19B73E96"));
		System.out.println(DESEncrypt.DecodeBankCard("06C4141C901CD44E6FE111EBE7CF8D58A38A777B240DDC8667C46B59FDA81A7E4C6B0442E3A9ADD56208293ACC41615E240F7A7D9F471BF0"));
		System.out.println("------------------解密2---------------");
		Account ac = new Account();
		ac.setBankAccount(bankCard);
		System.out.println(ac.getBankAccount());*/
//		System.out.println(DESEncrypt.DecodeBankCard("FDEE4E4715474BC8D2053F325F8D6F3C6AC3F12B86FB47CD9D50EAD03614CB9C4C6B0442E3A9ADD56208293ACC41615E240F7A7D9F471BF0"));
	//	System.out.println(DESEncrypt.DecodeIdCard("E1129D1139E1BBCF02C280BC4A8384FB23AF7BDC91D3A4EFEA983849544AC83C836F3C4AA52EEA98C0FE835F2C3599A4FE07908B19B73E96"));
//		System.out.println(DESEncrypt.EncodeUsername("ZHANGSAN"));
//		System.out.println(DESEncrypt.EncodeUsername("awb666666"));
//		System.out.println(DESEncrypt.EncodeProperties("3e128c281ecf96ee"));
//		System.out.println(DESEncrypt.DecodeProperties("D240A71CCF84A949E40102EABE2D64419EDDE01FF43651E9B7894C4A673663BD86F31ACF145B0655B92FB9E58ABE608842599D8BA4A8EA49"));
//		allyoubank@163.com
		//System.out.println(DESEncrypt.DecodePassword("B95EA69DA9F62CF4A5ACD878619F7EADF65BAFD4124144175D9F31BF4423184E42599D8BA4A8EA49"));
		//071BEB72FF78C01A2055F19102DBAC575A566DB14DE789A13B5695B6CCD0910442599D8BA4A8EA49
		System.out.println(DESEncrypt.EncodePassword("17000000002"));//15880133133
		/*String url = "91E3066C9AC97982702BCC3EA1DC2079BEB5CBA4CDEB174790920C63D18C93BB42599D8BA4A8EA49";
		System.out.println(new BASE64Encoder().encode(url.getBytes()));*/
		
//		System.out.println(DESEncrypt.EncodeProperties("115.159.83.172:3306"));
//System.out.println(DESEncrypt.DecodeProperties("91CC11A33FE1620539F7B6A39F5C6FA98DDD4F8488980A956AA6553F4B82D76660F8963A93E374ABD52C79538BF44D7842599D8BA4A8EA49"));
//		System.out.println(DESEncrypt.DecodeProperties("3E91FBD572B9C947BF4B74B8046F523A86F31ACF145B0655B92FB9E58ABE608842599D8BA4A8EA49"));
//		System.out.println(DESEncrypt.DecodeProperties("96714DE3B0EC57D136B4C2A4D1962CE8134FDE860800976C00104D20C00D211342599D8BA4A8EA49"));
//		System.out.println(DESEncrypt.EncodeProperties("baiyimao"));
//		//System.out.println(DESEncrypt.DecodeProperties("3E91FBD572B9C947BF4B74B8046F523A86F31ACF145B0655B92FB9E58ABE608842599D8BA4A8EA49"));
//		System.out.println(DESEncrypt.EncodeProperties("baiyimao26"));
		//System.out.println(DESEncrypt.DecodeProperties("3E91FBD572B9C947BF4B74B8046F523ACAD1A47B902D07660AD0CC9C1F163FA542599D8BA4A8EA49"));
		//System.out.println(DESEncrypt.EncodeIdCard("913205813235905439"));
		//System.out.println(DESEncrypt.DecodeIdCard("A54A6B5042BE2F189DE2131FDC192BDF600D86D797137703A228272AAC93D16C4E99B4940D9ACAD2D38BFFD1633DA277FE07908B19B73E96"));
		
	}

	
}
