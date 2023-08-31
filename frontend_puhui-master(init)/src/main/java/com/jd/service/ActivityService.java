package com.jd.service;

import com.jd.bean.Activity;
import com.jd.bean.Message;

import java.util.List;

public interface ActivityService {
    List<Activity> showActivies();
    Activity findById(int id);
    List<Message> showMessages(String comNo);
}