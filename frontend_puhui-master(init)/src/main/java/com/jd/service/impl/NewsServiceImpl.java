package com.jd.service.impl;

import com.jd.bean.News;
import com.jd.dao.NewsMapper;
import com.jd.service.NewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class NewsServiceImpl implements NewsService {

    @Resource
    private NewsMapper newsMapper;

    @Override
    public List<News> findByType(int type) {
        return newsMapper.findNewsByType(type);
    }

    @Override
    public List<News> findById(int id) {
        return newsMapper.findNewsById(id);
    }
}
