package com.awb.util;


import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/10/23.
 */
public class DecimalUtil {
    /**
     * 对两个数进行四则运算; <strong>对于除法的运算,保留4位小数;</strong><br>
     * result = num1+num2;<br>
     * result = num1-num2;<br>
     * result = num1*num2;<br>
     * result = num1/num2;<br>
     *
     * @param num1       式子中的第一个数;
     * @param num2       式子中的第二个数;
     * @param calcSymbol 运算符号 "+" "-" "*" "/"
     * @return BigDecimal
     * @throws Exception 计算异常;
     */
    public static BigDecimal calcNumber(Object num1, Object num2, String calcSymbol) throws Exception {
        return calcNumber(num1, num2, calcSymbol, 4);

    }

    /**
     * 对两个数进行四则运算; 自行选择保留位数;不四舍五入,直接截取; result = num1+num2;<br>
     * result = num1-num2;<br>
     * result = num1*num2;<br>
     * result = num1/num2;<br>
     *
     * @param num1       式子中的第一个数;
     * @param num2       式子中的第二个数;
     * @param calcSymbol 运算符号 "+" "-" "*" "/"
     * @param remainNum  保留多少位小数; 如果小于0,则为0;
     * @return BigDecimal
     * @throws Exception 计算异常;
     */
    public static BigDecimal calcNumber(Object num1, Object num2, String calcSymbol, int remainNum) throws Exception {
        remainNum = remainNum < 0 ? 0 : remainNum;
        if (!StringUtils.isEmpty(num1) && !StringUtils.isEmpty(num2)) {
            BigDecimal decimal = new BigDecimal(num1.toString());
            BigDecimal decima2 = new BigDecimal(num2.toString());
            if ("+".equals(calcSymbol)) {
                return decimal.add(decima2);
            } else if ("-".equals(calcSymbol)) {
                return decimal.subtract(decima2);
            } else if ("*".equals(calcSymbol)) {
                return decimal.multiply(decima2);
            } else if ("/".equals(calcSymbol)) {
                if (!num2.equals("0")) {
         return decimal.divide(decima2, remainNum, BigDecimal.ROUND_DOWN);
                } else {
                    throw new Exception();
                }
            } else {
                return null;
            }
        }
        return null;
    }
    public static BigDecimal calcNumber1(Object num1, Object num2, String calcSymbol, int remainNum) throws Exception {
        remainNum = remainNum < 0 ? 0 : remainNum;
        if (!StringUtils.isEmpty(num1) && !StringUtils.isEmpty(num2)) {
            BigDecimal decimal = new BigDecimal(num1.toString());
            BigDecimal decima2 = new BigDecimal(num2.toString());
            if ("+".equals(calcSymbol)) {
                return decimal.add(decima2);
            } else if ("-".equals(calcSymbol)) {
                return decimal.subtract(decima2);
            } else if ("*".equals(calcSymbol)) {
                return decimal.multiply(decima2);
            } else if ("/".equals(calcSymbol)) {
                if (!num2.equals("0")) {
                    return decimal.divide(decima2, remainNum, BigDecimal.ROUND_DOWN);
                } else {
                    throw new Exception();
                }
            } else {
                return null;
            }
        }
        return null;
    }
    public  static BigDecimal div(String s1, String s2){
        BigDecimal a= new BigDecimal(s1);
        BigDecimal b = new BigDecimal(s2);
        return a.divide(b, 2, RoundingMode.HALF_UP);
    }
    public  static BigDecimal div2(String s1, String s2){
        BigDecimal a= new BigDecimal(s1);
        BigDecimal b = new BigDecimal(s2);
        return a.divide(b, 0, RoundingMode.HALF_UP);
    }

    public  static BigDecimal div3(String s1, String s2){
        BigDecimal a= new BigDecimal(s1);
        BigDecimal b = new BigDecimal(s2);
        return a.divide(b, 3, RoundingMode.FLOOR);
    }
    public  static BigDecimal div4(String s1, String s2){
        BigDecimal a= new BigDecimal(s1);
        BigDecimal b = new BigDecimal(s2);
        return a.divide(b, 0, RoundingMode.FLOOR);
    }
    public  static BigDecimal div5(String s1, String s2){
        BigDecimal a= new BigDecimal(s1);
        BigDecimal b = new BigDecimal(s2);
        return a.divide(b, 0, RoundingMode.UP);
    }
    public static void main(String[] args) throws Exception {
       ;
        System.out.println( DecimalUtil.calcNumber(333,0.15,"*",2).doubleValue());
        System.out.println(DecimalUtil.div2("1","2"));
//        String price=DecimalUtil.calcNumber(1,0.01,"*").toString();
//        String money=DecimalUtil.calcNumber(price,100,"*").toString();
//        System.out.println(money);
       // total = DecimalUtil.calcNumber(admUserShop.getNum(), oldnum, "+").toString();
//        System.out.println(div3("2","3"));
//        String scale=DecimalUtil.calcNumber(398+"",20+"","*").toString();
//        System.out.println(scale);
//        System.out.println(25126*0.01);
        //System.out.println(DecimalUtil.div("100000","15").toString());
//        System.out.println(DecimalUtil.calcNumber(15,2,"*").toString());
//        System.out.println(DecimalUtil.div("150000","30").toString());
//        System.out.println(DecimalUtil.div("100000","30").toString());
//        System.out.println(DecimalUtil.div("50000","30").toString());
//        System.out.println(DecimalUtil.div("40000","30").toString());
//        System.out.println(DecimalUtil.div("70000","30").toString());
//        System.out.println(DecimalUtil.div("90000","30").toString());
//        System.out.println(DecimalUtil.div("100000","30").toString());

       // System.out.println(calcNumber(1,1.01,"-").doubleValue());
//        Pattern pattern=Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式
//        Matcher match=pattern.matcher("sss");
//        if(match.matches()==false){
//            System.out.println(0);
//        }else{
//            System.out.println(1);
//        }
//String source="12.556";
//        Pattern pattern = Pattern.compile("^[+]?([0-9]+(.[0-9]{1,2})?)$");
//        if(pattern.matcher(source).matches()){
//            System.out.println(1);
//        }else {
//            System.out.println(0);
//        }

        // String b=DecimalUtil.calcNumber(DecimalUtil.calcNumber(20000,130000,"*"),100,"*").toString();
//        String q="50000";
//        System.out.println(DecimalUtil.div2(q,"20").intValue());;
    }
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
