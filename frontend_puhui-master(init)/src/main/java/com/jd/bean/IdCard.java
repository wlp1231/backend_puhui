package com.jd.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdCard {
    private int id;
    private String username;
    private String idcard;
    private String period;
    private int comId;
}
