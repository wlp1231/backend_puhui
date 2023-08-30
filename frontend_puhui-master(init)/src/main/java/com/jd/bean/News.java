package com.jd.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class News {
    private int nId;
    private String newsTitle;
    private String newsDetail;
    private String newsState;
    private String newsType;
    private Date updateTime;
}