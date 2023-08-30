package com.jd.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
    private int aId;
    private Date beginDate;
    private Date endDate;
    private String actTitle;
    private String actDetail;
    private String actState;
    private Date updateTime;
}