package com.jd.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShareRule {
    private int sId;
    private int beginNum;
    private int endNum;
    private String shareReward;
}
