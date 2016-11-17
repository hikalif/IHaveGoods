package cn.edu.xmu.nongge.ihavegoods.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created by asus1 on 2016/10/27.
 */
public class Goods {
    @JSONField(name = "id")
    private long id;
    @JSONField(name = "type")
    private String type;
    @JSONField(name = "description")
    private String description;
    @JSONField(name = "weight")
    private String weight;
    @JSONField(name = "orderdeadline")
    private Date orderdeadline;
    @JSONField(name = "deliverydeadline")
    private Date deliverydeadline;
    @JSONField(name = "quotation")
    private String quotation;
    @JSONField(name = "trucktypedemand")
    private String trucktypedemand;
    @JSONField(name = "shippingAddressid")
    private long shippingAddressid;
    @JSONField(name = "receiptAddressid")
    private long receiptAddressid;
    @JSONField(name = "employerid")
    private long employerid;
    @JSONField(name = "quotationCount")
    private int quotationCount;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Date getOrderdeadline() {
        return orderdeadline;
    }

    public void setOrderdeadline(Date orderdeadline) {
        this.orderdeadline = orderdeadline;
    }

    public Date getDeliverydeadline() {
        return deliverydeadline;
    }

    public void setDeliverydeadline(Date deliverydeadline) {
        this.deliverydeadline = deliverydeadline;
    }

    public String getQuotation() {
        return quotation;
    }

    public void setQuotation(String quotation) {
        this.quotation = quotation;
    }

    public String getTrucktypedemand() {
        return trucktypedemand;
    }

    public void setTrucktypedemand(String trucktypedemand) {
        this.trucktypedemand = trucktypedemand;
    }

    public long getShippingAddressid() {
        return shippingAddressid;
    }

    public void setShippingAddressid(long shippingAddressid) {
        this.shippingAddressid = shippingAddressid;
    }

    public long getReceiptAddressid() {
        return receiptAddressid;
    }

    public void setReceiptAddressid(long receiptAddressid) {
        this.receiptAddressid = receiptAddressid;
    }

    public long getEmployerid() {
        return employerid;
    }

    public void setEmployerid(long employerid) {
        this.employerid = employerid;
    }

    public int getQuotationCount() {
        return quotationCount;
    }

    public void setQuotationCount(int quotationCount) {
        this.quotationCount = quotationCount;
    }
}
