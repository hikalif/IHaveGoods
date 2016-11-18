package cn.edu.xmu.nongge.ihavegoods.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created by Yui on 2016/11/17.
 */
public class Quotation {
    @JSONField(name = "quotationTime")
    private Date quotationTime;
    @JSONField(name = "truckCarplate")
    private String truckCarplate;
    @JSONField(name = "truckLoad")
    private int truckLoad;
    @JSONField(name = "truckType")
    private String truckType;
    @JSONField(name = "truckerCredit")
    private double truckerCredit;
    @JSONField(name = "truckerHeadicon")
    private String truckerHeadicon;
    @JSONField(name = "truckerName")
    private String truckerName;
    private int truckerOrdercount;
    @JSONField(name = "truckerTelephone")
    private String truckerTelephone;
    @JSONField(name = "waybillPrice")
    private double waybillPrice;
    @JSONField(name = "waybillRemark")
    private String waybillRemark;
    @JSONField(name = "waybillid")
    private long waybillid;

    public long getWaybillid() {
        return waybillid;
    }

    public void setWaybillid(long waybillid) {
        this.waybillid = waybillid;
    }

    public double getWaybillPrice() {
        return waybillPrice;
    }

    public void setWaybillPrice(double waybillPrice) {
        this.waybillPrice = waybillPrice;
    }

    public String getTruckerTelephone() {
        return truckerTelephone;
    }

    public void setTruckerTelephone(String truckerTelephone) {
        this.truckerTelephone = truckerTelephone;
    }

    public String getTruckerName() {
        return truckerName;
    }

    public void setTruckerName(String truckerName) {
        this.truckerName = truckerName;
    }

    public String getTruckType() {
        return truckType;
    }

    public void setTruckType(String truckType) {
        this.truckType = truckType;
    }

    public int getTruckLoad() {
        return truckLoad;
    }

    public void setTruckLoad(int truckLoad) {
        this.truckLoad = truckLoad;
    }

    public String getTruckCarplate() {
        return truckCarplate;
    }

    public void setTruckCarplate(String truckCarplate) {
        this.truckCarplate = truckCarplate;
    }

    public Date getQuotationTime() {
        return quotationTime;
    }

    public void setQuotationTime(Date quotationTime) {
        this.quotationTime = quotationTime;
    }

    public String getWaybillRemark() {
        return waybillRemark;
    }

    public void setWaybillRemark(String waybillRemark) {
        this.waybillRemark = waybillRemark;
    }

    public int getTruckerOrdercount() {
        return truckerOrdercount;
    }

    public void setTruckerOrdercount(int truckerOrdercount) {
        this.truckerOrdercount = truckerOrdercount;
    }

    public String getTruckerHeadicon() {
        return truckerHeadicon;
    }

    public void setTruckerHeadicon(String truckerHeadicon) {
        this.truckerHeadicon = truckerHeadicon;
    }

    public double getTruckerCredit() {
        return truckerCredit;
    }

    public void setTruckerCredit(double truckerCredit) {
        this.truckerCredit = truckerCredit;
    }
}
