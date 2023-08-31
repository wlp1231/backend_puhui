package com.jd.bean;

/**
 * 风控对象
 */
public class Risk {
    private int riskId;  //主键
    private String loaNo;  //贷款编号
    private String comName;  //企业名称
    private Long loaMoney;  //贷款金额
    private Long loaLisId;   //贷款周期
    private String aptPrice;  //抵押物价值
    private int monthAccountMin;  //月流水最小值
    private String grossLiabilities;  //总负债值
    private String creditLine;   //额度值
    private String riskDel = "0";  //是否删除 0存在 2删除

    public String getRiskDel() {
        return riskDel;
    }

    public void setRiskDel(String riskDel) {
        this.riskDel = riskDel;
    }

    public Risk() {
    }

    public Risk(String loaNo, String comName, Long loaMoney, Long loaLisId, String riskDel) {
        this.loaNo = loaNo;
        this.comName = comName;
        this.loaMoney = loaMoney;
        this.loaLisId = loaLisId;
        this.riskDel = riskDel;
    }

    @Override
    public String toString() {
        return "Risk{" +
                "riskId=" + riskId +
                ", loaNo='" + loaNo + '\'' +
                ", comName='" + comName + '\'' +
                ", loaMoney=" + loaMoney +
                ", loaLisId=" + loaLisId +
                ", aptPrice='" + aptPrice + '\'' +
                ", monthAccountMin=" + monthAccountMin +
                ", grossLiabilities='" + grossLiabilities + '\'' +
                ", creditLine='" + creditLine + '\'' +
                ", riskDel='" + riskDel + '\'' +
                '}';
    }

    public int getRiskId() {
        return riskId;
    }

    public void setRiskId(int riskId) {
        this.riskId = riskId;
    }

    public String getLoaNo() {
        return loaNo;
    }

    public void setLoaNo(String loaNo) {
        this.loaNo = loaNo;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public Long getLoaMoney() {
        return loaMoney;
    }

    public void setLoaMoney(Long loaMoney) {
        this.loaMoney = loaMoney;
    }

    public Long getLoaLisId() {
        return loaLisId;
    }

    public void setLoaLisId(Long loaLisId) {
        this.loaLisId = loaLisId;
    }

    public String getAptPrice() {
        return aptPrice;
    }

    public void setAptPrice(String aptPrice) {
        this.aptPrice = aptPrice;
    }

    public int getMonthAccountMin() {
        return monthAccountMin;
    }

    public void setMonthAccountMin(int monthAccountMin) {
        this.monthAccountMin = monthAccountMin;
    }

    public String getGrossLiabilities() {
        return grossLiabilities;
    }

    public void setGrossLiabilities(String grossLiabilities) {
        this.grossLiabilities = grossLiabilities;
    }

    public String getCreditLine() {
        return creditLine;
    }

    public void setCreditLine(String creditLine) {
        this.creditLine = creditLine;
    }
}
