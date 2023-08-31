package com.jd.service.impl;

import com.jd.bean.Activity;
import com.jd.bean.Message;
import com.jd.dao.ActivityMapper;
import com.jd.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    ActivityMapper activityMapper;
    @Override
    public List<Activity> showActivies() {
        System.out.println("safsdafsdaf");
        System.out.println(activityMapper.showActivies());
        System.out.println("22222222222222222");
        return activityMapper.showActivies();
    }

    @Override
    public Activity findById(int id) {
        return activityMapper.findById(id);
    }

    @Override
    public List<Message> showMessages(String comNo) {
        return activityMapper.showMessages(comNo);
    }
}
