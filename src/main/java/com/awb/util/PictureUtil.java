package com.awb.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2018/12/5.
 */
public class PictureUtil {
    public static void main(String[] args) {
        PictureUtil.draw("d:/pic/123/123.jpg","d:/pic/mm.jpg","高级会员","15683232121","王永利");
    }
   public static void draw(String imagePath,String path,String level,String phone,String name){

        //读取图片文件，得到BufferedImage对象
        BufferedImage bimg;
       //  FileOutputStream fileOutputStream=null;
  BufferedOutputStream bos = null;
        try {
            bimg = ImageIO.read(new FileInputStream(imagePath));//"http://47.98.177.63:883/upload/voucher/png/123.jpg"

            Graphics2D g2d=(Graphics2D)bimg.getGraphics();
            //设置颜色和画笔粗细
            g2d.setColor(Color.black);
            g2d.setStroke(new BasicStroke(3));
            g2d.setFont(new Font("宋体", Font.BOLD, 60));//90
            //绘制图案或文字
            g2d.drawString(name, 580, 1179);//143  530  930, 1890
            //保存新图片
            Graphics2D a=(Graphics2D)bimg.getGraphics();
            a.setColor(Color.black);
            a.setStroke(new BasicStroke(3));
            a.setFont(new Font("宋体", Font.ITALIC, 60));
            //绘制图案或文字
            a.drawString(phone, 580, 1255);//380  530 2010
            Graphics2D b=(Graphics2D)bimg.getGraphics();
            b.setColor(Color.black);
            b.setStroke(new BasicStroke(3));
            b.setFont(new Font("宋体", Font.BOLD, 60));
            //绘制图案或文字
            b.drawString(level, 580, 1330);//170   580
//            ImageIO.write(bimg, "JPG",new FileOutputStream(path));
            FileOutputStream    fileOutputStream=new FileOutputStream(path);
            bos = new BufferedOutputStream(fileOutputStream);
            ImageIO.write(bimg, "JPG",bos);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(bos!=null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }


}
