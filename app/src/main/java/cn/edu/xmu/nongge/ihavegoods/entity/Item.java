package cn.edu.xmu.nongge.ihavegoods.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by Yui on 2016/11/17.
 */
public class Item {
    @JSONField(name = "id")
    private long id;
    @JSONField(name = "description")
    private String description;
    @JSONField(name = "picture")
    private String picture;
    @JSONField(name = "quotation_count")
    private int quotation_count;
    @JSONField(name = "type")
    private String type;
    @JSONField(name = "quotationItemList")
    private List<Quotation> quotationList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getQuotation_count() {
        return quotation_count;
    }

    public void setQuotation_count(int quotation_count) {
        this.quotation_count = quotation_count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Quotation> getQuotationList() {
        return quotationList;
    }

    public void setQuotationList(List<Quotation> quotationList) {
        this.quotationList = quotationList;
    }
}
