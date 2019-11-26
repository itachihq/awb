package com.awb.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;

/**
 * Created by Administrator on 2018/11/26.
 */
public class EwmUtil {
    public static void main(String[] args) {
   System.out.println(getEwm("http://web.myaiwoba.com/aiwoba/pages/user/login.html",""));
    }
    public  static String getEwm(String content,String env){
        final int width = 400;
        final int height = 400;
        final String format = "png";
    //    final String content = "http://www.baidu.com";

        //定义二维码的参数
        HashMap hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 2);

        //生成二维码
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            File destFile = null;

            String os = System.getProperty("os.name");
            String str="";
            if(os.toLowerCase().startsWith("win")){
                destFile = new File("D:/descFolder/"+ UuidUtil.get32UUID()+".png");
            }else{
                if("test".equals(env)){
                    destFile = new File("/usr/local/upload/voucher/test/ewm/"+ CommUtil.getUniqueNumberByRandom()+".png");
                  str=  destFile.toString().replace("/usr/local","http://47.98.177.63:882");
                }else {
                    destFile = new File("/usr/local/upload/voucher/ewm/"+ CommUtil.getUniqueNumberByRandom()+".png");///upload/voucher/test/
                    str=destFile.toString().replace("/usr/local","http://47.98.177.63:883");
                }

            }

            File parent = destFile.getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }
            Path file =destFile.toPath();
            MatrixToImageWriter.writeToPath(bitMatrix, format, file);
            return  str;
        } catch (Exception e) {

        }
        return null;
    }
}
