package com.jd.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShareRecord {
    private int rId;
    private int comId;
    private int shareNum;
    private int updateTime;

}
