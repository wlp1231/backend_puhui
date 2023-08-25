package com.jd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication
@ImportResource(locations={"classpath:mykaptcha.xml"})
@MapperScan("com.jd.dao")
public class JindongApplication implements ServletContextInitializer {

    public static void main(String[] args) {
        SpringApplication.run(JindongApplication.class, args);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.setInitParameter("org.apache.tomcat.websocket.textBufferSize","1024000");
    }
}
