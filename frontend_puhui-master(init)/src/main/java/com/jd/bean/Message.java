package com.jd.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private int mId;
    private int comId;
    private String msgDetail;
    private String msgTime;
    private String respDetail;
    private String respTime;
    private String msgState;
}