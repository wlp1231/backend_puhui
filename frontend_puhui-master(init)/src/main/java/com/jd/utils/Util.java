package com.jd.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Util {
    // 画上点
    public static void drawPoint(BufferedImage bfi) {
        int area = (int) (0.02 * 80 * 25);
        for (int i = 0; i < area; ++i) {
            int x = (int) (Math.random() * 80);
            int y = (int) (Math.random() * 25);
            bfi.setRGB(x, y, (int) (Math.random() * 255));
        }
    }

    public static void drawInfluencedLine(Graphics2D g) {
        //设置验证码中的干扰线
        for (int i = 0; i < 3; i++) {
            Random random = new Random();
            //随机获取干扰线的起点和终点
            int xstart = random.nextInt(80);
            int ystart = random.nextInt(25);
            int xend = random.nextInt(80);
            int yend = random.nextInt(25);
            g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            g.drawLine(xstart, ystart, xend, yend);
        }
    }

    // 生成随机数旋转后写入
    public static String drawRandomNum(Graphics2D g) {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        Random random = new Random();
        int index;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            // 从字符数组中获得随机字符
            index = random.nextInt(chars.length);
            String value = chars[index] + "";
            // 设置随机字符的颜色
            g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            // 字体格式
            Font font = new Font(null, Font.BOLD, 20);
            g.setFont(font);
            //生成-30~30之间的数字
            int dgree = random.nextInt(60) - 30;
            //旋转
            g.rotate(dgree * Math.PI / 180, i * 20 + 2, 23);
            // 写入字符
            g.drawString(value, i * 20 + 2, 20);
            // 把画笔再转回去,免得影响下次的弧度
            g.rotate(-dgree * Math.PI / 180, i * 20 + 2, 23);
            builder.append(value);
        }
        return builder.toString();
    }

}
