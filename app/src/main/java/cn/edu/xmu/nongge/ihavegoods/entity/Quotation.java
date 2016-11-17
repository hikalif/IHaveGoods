package cn.edu.xmu.nongge.ihavegoods.entity;

import java.util.Date;

/**
 * Created by Yui on 2016/11/17.
 */
public class Quotation {
    private Date quotationTime;
    private String truckCarplate;
    private int truckLoad;
    private String truckType;
    private String truckerName;
    private String truckerTelephone;
    private double waybillPrice;
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
}
