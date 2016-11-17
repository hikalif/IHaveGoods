package cn.edu.xmu.nongge.ihavegoods.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created by Yui on 2016/11/10.
 */
public class Waybill {
    @JSONField(name = "id")
    private long id;
    @JSONField(name = "goodsid")
    private long goodsid;
    @JSONField(name = "truckid")
    private long truckid;
    @JSONField(name = "price")
    private double price;
    @JSONField(name = "status")
    private int status;
    @JSONField(name = "receiptTime")
    private Date receiptTime;
    @JSONField(name = "payway")
    private int payway;
    @JSONField(name = "logisticsid")
    private long logisticsid;
    @JSONField(name = "credit")
    private double credit;
    @JSONField(name = "quotationTime")
    private Date quotationTime;
    @JSONField(name = "remark")
    private String remark;
    @JSONField(name = "comment")
    private String comment;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(long goodsid) {
        this.goodsid = goodsid;
    }

    public long getTruckid() {
        return truckid;
    }

    public void setTruckid(long truckid) {
        this.truckid = truckid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getReceiptTime() {
        return receiptTime;
    }

    public void setReceiptTime(Date receiptTime) {
        this.receiptTime = receiptTime;
    }

    public int getPayway() {
        return payway;
    }

    public void setPayway(int payway) {
        this.payway = payway;
    }

    public long getLogisticsid() {
        return logisticsid;
    }

    public void setLogisticsid(long logisticsid) {
        this.logisticsid = logisticsid;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public Date getQuotationTime() {
        return quotationTime;
    }

    public void setQuotationTime(Date quotationTime) {
        this.quotationTime = quotationTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
