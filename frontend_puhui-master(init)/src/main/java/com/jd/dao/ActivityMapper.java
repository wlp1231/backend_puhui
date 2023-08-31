package com.jd.dao;

import com.jd.bean.Activity;
import com.jd.bean.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActivityMapper {
    List<Activity> showActivies();
    Activity findById(int id);
    List<Message> showMessages(String comNo);
}