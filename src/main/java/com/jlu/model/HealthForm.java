package com.jlu.model;

import com.jlu.annotation.Standard;

/**
 * Created by Administrator on 2018/5/28.
 */
public class HealthForm {

    @Standard(keyWord = "日期", dataRegex = "\\d+年\\d+月\\d+日")
    private String time;
    @Standard(keyWord = "身高")
    private String height;
    @Standard(keyWord = "体重")
    private String weight;
    @Standard(keyWord = "腰围")
    private String waistline;
    @Standard(keyWord = "收缩压")
    private String systolicPressure;
    @Standard(keyWord = "舒张压")
    private String diastolicPressure;
    @Standard(keyWord = "血红蛋白")
    private String hemoglobin;
    @Standard(keyWord = "白细胞")
    private String whiteBloodCell;
    @Standard(keyWord = "血小板")
    private String platelet;
    @Standard(keyWord = "尿蛋白", dataRegex = "阴性|阳性")
    private String urineProtein;
    @Standard(keyWord = "尿糖", dataRegex = "阴性|阳性")
    private String urineSugar;
    @Standard(keyWord = "尿酮体", dataRegex = "阴性|阳性")
    private String urineKetoneBody;
    @Standard(keyWord = "空腹血糖", dataRegex = "\\d+\\.\\d+")
    private String fastingBloodGlucose;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWaistline() {
        return waistline;
    }

    public void setWaistline(String waistline) {
        this.waistline = waistline;
    }

    public String getSystolicPressure() {
        return systolicPressure;
    }

    public void setSystolicPressure(String systolicPressure) {
        this.systolicPressure = systolicPressure;
    }

    public String getDiastolicPressure() {
        return diastolicPressure;
    }

    public void setDiastolicPressure(String diastolicPressure) {
        this.diastolicPressure = diastolicPressure;
    }

    public String getHemoglobin() {
        return hemoglobin;
    }

    public void setHemoglobin(String hemoglobin) {
        this.hemoglobin = hemoglobin;
    }

    public String getWhiteBloodCell() {
        return whiteBloodCell;
    }

    public void setWhiteBloodCell(String whiteBloodCell) {
        this.whiteBloodCell = whiteBloodCell;
    }

    public String getPlatelet() {
        return platelet;
    }

    public void setPlatelet(String platelet) {
        this.platelet = platelet;
    }

    public String getUrineProtein() {
        return urineProtein;
    }

    public void setUrineProtein(String urineProtein) {
        this.urineProtein = urineProtein;
    }

    public String getUrineSugar() {
        return urineSugar;
    }

    public void setUrineSugar(String urineSugar) {
        this.urineSugar = urineSugar;
    }

    public String getUrineKetoneBody() {
        return urineKetoneBody;
    }

    public void setUrineKetoneBody(String urineKetoneBody) {
        this.urineKetoneBody = urineKetoneBody;
    }

    public String getFastingBloodGlucose() {
        return fastingBloodGlucose;
    }

    public void setFastingBloodGlucose(String fastingBloodGlucose) {
        this.fastingBloodGlucose = fastingBloodGlucose;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HealthForm{");
        sb.append("time='").append(time).append('\'');
        sb.append(", height=").append(height);
        sb.append(", weight=").append(weight);
        sb.append(", waistline=").append(waistline);
        sb.append(", systolicPressure=").append(systolicPressure);
        sb.append(", diastolicPressure=").append(diastolicPressure);
        sb.append(", hemoglobin=").append(hemoglobin);
        sb.append(", whiteBloodCell=").append(whiteBloodCell);
        sb.append(", platelet=").append(platelet);
        sb.append(", urineProtein='").append(urineProtein).append('\'');
        sb.append(", urineSugar='").append(urineSugar).append('\'');
        sb.append(", urineKetoneBody='").append(urineKetoneBody).append('\'');
        sb.append(", fastingBloodGlucose='").append(fastingBloodGlucose).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
