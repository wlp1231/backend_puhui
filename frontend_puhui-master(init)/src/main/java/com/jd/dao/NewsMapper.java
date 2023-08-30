package com.jd.dao;

import com.jd.bean.Activity;
import com.jd.bean.News;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsMapper {

    List<News> findNewsByType(int type);

    List<News> findNewsById(int id);
}