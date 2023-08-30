package com.jd.service;

import com.jd.bean.News;

import java.util.List;

public interface NewsService {

    List<News> findByType(int type);

    List<News> findById(int id);
}