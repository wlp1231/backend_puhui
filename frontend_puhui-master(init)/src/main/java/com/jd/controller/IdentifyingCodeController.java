package com.jd.controller;

import com.jd.utils.Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class IdentifyingCodeController {

    @RequestMapping(value = "IdentifyingCodeServlet",method = RequestMethod.GET)
    public void yanZhenMa(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedImage bfi = new BufferedImage(80, 34, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = (Graphics2D) bfi.getGraphics();

        //设置背景颜色
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 80, 34);

        //设置边框
        graphics.setColor(Color.BLACK);
        graphics.drawRect(0, 0, 80 - 1, 34 - 1);

        String str = Util.drawRandomNum(graphics);

        request.getSession().setAttribute("identifyCode", str);
        System.out.println("注册验证码："+str);
        Util.drawPoint(bfi);
        Util.drawInfluencedLine(graphics);
        response.setHeader("Expires", "-1");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");

        response.setHeader("content-type", "img/jpeg");

        ImageIO.write(bfi, "jpg", response.getOutputStream());
    }
}
